package ch04.ex10;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Browser extends Application {

	private final String INITIAL_PAGE = "https://www.google.co.jp";

	@Override
	public void start(Stage primaryStage) throws Exception {
		WebView browser = new WebView();
		WebEngine engine = browser.getEngine();
		WebHistory history = engine.getHistory();
		TextField url = new TextField(INITIAL_PAGE);
		url.setOnAction(e -> engine.load(url.getText()));
		engine.load(INITIAL_PAGE);

		Button backButton = new Button("戻る");
		backButton.setOnAction(e -> {
			if (history.getCurrentIndex() > 0) {
			    history.go(-1);
			    url.setText(history.getEntries().get(history.getCurrentIndex()).getUrl());
			}
		});

		VBox box = new VBox();
		FlowPane header = new FlowPane();
		header.getChildren().addAll(backButton, url);
		box.getChildren().addAll(header, browser);

		Scene scene = new Scene(box);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
