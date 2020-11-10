package ch03.ex12;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class LatentImage {

	private Image in;
	private List<ColorTransform> pendingOperations = new ArrayList<>();

	private LatentImage(Image in) {
		this.in = in;
	}

	public static LatentImage from(Image in) {
		return new LatentImage(in);
	}

	public Image toImage() {
		int width = (int)in.getWidth();
		int height = (int)in.getHeight();
		if (pendingOperations.size() == 0) {
			return in;
		}
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Color c = in.getPixelReader().getColor(x, y);
				for (ColorTransform f : pendingOperations) {
					c = f.apply(x, y, c);
				}
				out.getPixelWriter().setColor(x, y, c);
			}
		}
		return out;
	}

	public LatentImage transform(UnaryOperator<Color> f) {
		//UnaryOperatorをColorTransformへ変換
		pendingOperations.add((x, y, c) -> f.apply(c) );
		return this;
	}

	public LatentImage transform(ColorTransform f) {
		pendingOperations.add(f);
		return this;
	}


}
