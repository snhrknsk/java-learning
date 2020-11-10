package ch03.ex06;

import java.io.File;
import java.io.IOException;
import java.util.function.BiFunction;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ImageTransform extends Application {

	public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg) {
		int width = (int)in.getWidth();
		int height = (int)in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y), arg));
            }
        }
		return out;
	}

	@Override
    public void start(Stage stage) throws Exception {
		System.out.println("Current Directory" + System.getProperty("user.dir"));
		Image img = new Image( new File("./src/ch03/ex06/queen-mary.png").toURI().toString() );
		Image result = ImageTransform.transform(img, (c, factor) -> c.deriveColor(0, 1, factor, 1), 1.2);

		String fmt = "png";
		File f = new File("./src/ch03/ex06/resutl" + "." + fmt);
	    try {
			ImageIO.write(SwingFXUtils.fromFXImage(result, null), fmt, f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    System.exit(0);
    }

	public static void main(String[] args) {
		launch(args);
	}
}
