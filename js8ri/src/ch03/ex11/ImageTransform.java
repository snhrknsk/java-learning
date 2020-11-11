package ch03.ex11;

import java.io.File;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ImageTransform extends Application {

	/**
	 * f1を実行しf2を実行する関数{@link ColorTransform}を返す
	 * @param f1 初めに実行する{@link ColorTransform}関数
	 * @param f2 f1の次に実行する{@link ColorTransform}関数
	 * @return
	 */
	public static ColorTransform combineColorTransform(ColorTransform f1, ColorTransform f2) {
		return (x, y, c) -> {
			return f2.apply(x, y, f1.apply(x, y, c));
		};
	}

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

	@Override
    public void start(Stage primaryStage) throws Exception {
		System.out.println("Current Directory" + System.getProperty("user.dir"));
		Image img = new Image( new File("./src/ch03/ex11/queen-mary.png").toURI().toString() );
		int frame = 10;
		Image result = ImageTransform.transform(img, combineColorTransform((x, y, c) -> {
			return c.brighter();
		}, (x, y, c) -> {
			if (x < frame || y < frame || x > img.getWidth() - frame || y > img.getHeight() - frame ) {
				return Color.GRAY;
			}
			return c;
		}));

		String fmt = "png";
		File f = new File("./src/ch03/ex11/resut" + "." + fmt);
		ImageIO.write(SwingFXUtils.fromFXImage(result, null), fmt, f);
	    System.exit(0);
    }

	public static void main(String[] args) {
		launch(args);
	}
}
