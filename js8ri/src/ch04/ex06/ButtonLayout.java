package ch04.ex06;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ButtonLayout extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane pane = new BorderPane();

		// Hboxを作成しボタンを真ん中に配置
		HBox topBox = new HBox();
		topBox.getChildren().add(new Button("Top"));
		topBox.setAlignment(Pos.CENTER);

		HBox bottomBox = new HBox();
		bottomBox.getChildren().add(new Button("Bottom"));
		bottomBox.setAlignment(Pos.CENTER);

		pane.setTop(topBox);
		pane.setLeft(new Button("Left"));
		pane.setCenter(new Button("Center"));
		pane.setRight(new Button("Right"));
		pane.setBottom(bottomBox);

		primaryStage.setScene(new Scene(pane));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
