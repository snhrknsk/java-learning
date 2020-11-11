package ch04.ex09;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PlanetAnimation extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Circle planet = new Circle(100, 10, 10);

		Ellipse orbit = new Ellipse();
		orbit.setCenterX(90.0f);
		orbit.setCenterY(10.0f);
		orbit.setRadiusX(100.0f);
		orbit.setRadiusY(50.0f);

		PathTransition animation = new PathTransition();
		animation.setDuration(Duration.millis(5000));
		animation.setNode(planet);
		animation.setPath(orbit);
		animation.setCycleCount(Animation.INDEFINITE);
		animation.setInterpolator(Interpolator.LINEAR);
		animation.play();

		BorderPane pane = new BorderPane();
		pane.setCenter(planet);
		Scene scene = new Scene(pane);
		primaryStage.setWidth(300);
		primaryStage.setHeight(200);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
