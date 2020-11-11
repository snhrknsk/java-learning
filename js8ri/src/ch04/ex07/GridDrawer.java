package ch04.ex07;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GridDrawer extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane pane = new GridPane();
		// グリッド表示
		pane.setGridLinesVisible(true);

		Label label = new Label("text:");
		Button button = new Button("Button");


		pane.add(label, 0, 0);
		pane.add(button, 1, 1);
		primaryStage.setScene(new Scene(pane));
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
