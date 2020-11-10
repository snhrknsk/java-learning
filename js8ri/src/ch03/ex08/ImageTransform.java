package ch03.ex08;

import java.io.File;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ImageTransform extends Application {

	public static Image transform( Image image, ColorTransform t ) {
		int width = (int)image.getWidth();
		int height = (int)image.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				out.getPixelWriter().setColor(x, y, t.apply(x, y, image.getPixelReader().getColor(x, y)));
			}
		}
		return out;
	}

	public static ColorTransform generateFrame(Image img, int frameSize) {
		return (x, y, c) -> {
			if (x < frameSize || y < frameSize || x >= img.getWidth() - frameSize || y >= img.getHeight() - frameSize ) {
				return Color.GRAY;
			}
			return c;
		};
	}

	@Override
    public void start(Stage stage) throws Exception {
		System.out.println("Current Directory : " + System.getProperty("user.dir"));
		Image img = new Image( new File("./src/ch03/ex08/queen-mary.png").toURI().toString() );
		Image result = ImageTransform.transform(img, generateFrame(img, 10));

		String fmt = "png";
		File f = new File("./src/ch03/ex08/resut" + "." + fmt);
		ImageIO.write(SwingFXUtils.fromFXImage(result, null), fmt, f);
	    System.exit(0);
    }

	public static void main(String[] args) {
		launch(args);
	}
}
