package ch04.ex01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavaFX extends Application{

    @Override
    public void start(Stage stage) {

		TextArea textArea = new TextArea("Hello, JavaFX!");
		Label label = new Label("Hello, JavaFX!");
		label.setFont(new Font(100));
		label.textProperty().bindBidirectional(textArea.textProperty());
		VBox box = new VBox();
		box.getChildren().addAll(label, textArea);

		Scene scene = new Scene(box);
		stage.setTitle("Hello,JavaFx");
		stage.setScene(scene);
		stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
