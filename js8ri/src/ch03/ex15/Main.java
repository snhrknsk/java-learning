package ch03.ex15;

import java.io.File;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
    public void start(Stage primaryStage) throws Exception {
		System.out.println("Current Directory" + System.getProperty("user.dir"));
		Image img = new Image( new File("./src/ch03/ex15/queen-mary.png").toURI().toString() );
		Image result = LatentParallelImage.from(img).transform(Color::brighter).transform(Color::brighter).toImage();

		String fmt = "png";
		File f = new File("./src/ch03/ex15/resut" + "." + fmt);
		ImageIO.write(SwingFXUtils.fromFXImage(result, null), fmt, f);
	    System.exit(0);
    }

	public static void main(String[] args) {
		launch(args);
	}

}
