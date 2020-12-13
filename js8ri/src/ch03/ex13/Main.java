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
			int pixels = 9;
			double red = 0;
			double green = 0;
			double blue = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					Color c = image.getColor(x + i - 1, y + j - 1);
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

			List<Color> colors = new ArrayList<>();

			colors.add(image.getColor(x, y + 1)); // N
			colors.add(image.getColor(x + 1, y)); // E
			colors.add(image.getColor(x, y - 1)); // S
			colors.add(image.getColor(x - 1, y)); // W
			Color color = image.getColor(x, y);

			double red = colors.size() * color.getRed();
			double green = colors.size() * color.getGreen();
			double blue = colors.size() * color.getBlue();
			for (Color c : colors) {
				if (c == null) {
					continue;
				}
				red -= c.getRed();
				green -= c.getGreen();
				blue -= c.getBlue();
			}
			return Color.color(
					red < 0 ? 0.0 : red > 1.0 ? 1.0 : red,
					green < 0 ? 0.0 : green > 1.0 ? 1.0 : green,
					blue < 0 ? 0.0 : blue > 1.0 ? 1.0 : blue
				);
		};

		LatentImage latentImage = LatentImage.from(img).transform(blurTransform).transform(edgeDetectTransform);

		Image result = latentImage.toImage();

		String fmt = "png";
		File f = new File("./src/ch03/ex13/resut" + "." + fmt);
		ImageIO.write(SwingFXUtils.fromFXImage(result, null), fmt, f);
	    System.exit(0);

	}

}
