package ch03.ex13;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class LatentImage {

	private final Image in;
	private final int width;
	private final int height;
	private WritableImage out;
	private Image bufferImage;
	private List<ColorTransform> pendingOperations = new ArrayList<>();

	private LatentImage(Image in) {
		this.in = in;
		bufferImage = in;
		width = (int)in.getWidth();
		height = (int)in.getHeight();
		out = new WritableImage(width, height);
	}

	public static LatentImage from(Image in) {
		return new LatentImage(in);
	}

	public Image toImage() {
		if (pendingOperations.size() == 0) {
			return in;
		}
		// ColorTransformに対する結果を途中結果としてbufferImageへ格納しすべての前のTransformを終了させる
		for (ColorTransform f : pendingOperations) {
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					Color c = bufferImage.getPixelReader().getColor(x, y);
					c = f.apply(x, y, this);
					out.getPixelWriter().setColor(x, y, c);
				}
			}
			bufferImage = out;
		}
		return out;
	}

	public LatentImage transform(ColorTransform f) {
		pendingOperations.add(f);
		return this;
	}

	public Color getColor(int x, int y) {
		if (x < 0 || width <= x) {
    		return null;
    	}
    	if (y < 0 || height <= y) {
    		return null;
    	}
		return bufferImage.getPixelReader().getColor(x, y);
	}
}
