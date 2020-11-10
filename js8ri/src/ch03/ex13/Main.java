package ch03.ex13;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		System.out.println("Current Directory" + System.getProperty("user.dir"));
		Image img = new Image( new File("./src/ch03/ex13/queen-mary.png").toURI().toString() );

		ColorTransform blurTransform = (x, y, image) -> {
			int width = (int)img.getWidth();
			int height = (int)img.getHeight();
			int pixels = 9;
			double red = 0;
			double green = 0;
			double blue = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					Color c = getColor(image, x + i - 1, y + j - 1, width, height);
					if (c != null) {
						red += c.getRed();
						green += c.getGreen();
						blue += c.getBlue();
					}
				}
			}
			return Color.color(red / pixels, green / pixels, blue / pixels);
		};

		ColorTransform edgeDetectTransform = (x, y, image) -> {
			int width = (int)img.getWidth();
			int height = (int)img.getHeight();
			int pixels = 4;
			List<Color> colors = new ArrayList<>();
			colors.add(getColor(image, x, y + 1, width, height)); // N
			colors.add( getColor(image, x + 1, y, width, height)); // E
			colors.add(getColor(image, x, y - 1, width, height)); // S
			colors.add(getColor(image, x - 1, y, width, height)); // W
			Color color = image.getColor(x, y);

			double red = 0.0;
			double green = 0.0;
			double blue = 0.0;
			for (Color c : colors) {
				if (c == null) {
					continue;
				}
				red += c.getRed();
				green += c.getGreen();
				blue += c.getBlue();
			}
			return Color.color(
					color.getRed() * pixels - red < 0 ? 0.0 : color.getRed() > 1.0 ? 1.0 : color.getRed(),
					color.getGreen() * pixels - green < 0 ? 0.0 : color.getGreen() > 1.0 ? 1.0 : color.getGreen(),
					color.getBlue() * pixels - blue < 0 ? 0.0 : color.getBlue() > 1.0 ? 1.0 : color.getBlue()
				);
		};

		LatentImage latentImage = LatentImage.from(img).transform(blurTransform).transform(edgeDetectTransform);

		Image result = latentImage.toImage();

		String fmt = "png";
		File f = new File("./src/ch03/ex13/resut" + "." + fmt);
		ImageIO.write(SwingFXUtils.fromFXImage(result, null), fmt, f);
	    System.exit(0);

	}

    private static Color getColor(LatentImage image, int x, int y, int width, int height) {
    	if (x < 0 || width <= x) {
    		return null;
    	}
    	if (y < 0 || height <= y) {
    		return null;
    	}
    	return image.getColor(x, y);
    }

}
