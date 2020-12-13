package dc3_1.src;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DigitalWatch extends Application{

	private Calendar time = Calendar.getInstance(Locale.JAPAN);
	private final Format FORMAT = new SimpleDateFormat("HH:mm:ss");
	private final int UPDATE_INTERVAL = 1000;

	//Component
	private Label timeLabel;

    @Override
    public void start(Stage stage) {

    	createComponent(stage);
    	startWatch();
		stage.show();
    }

    @Override
    public void stop(){

    }

    public static void main(String[] args) {
        launch(args);
    }

    private void createComponent(Stage stage) {
    	GridPane panel = new GridPane();
    	panel.setAlignment(Pos.CENTER);

		timeLabel = new Label(FORMAT.format(time.getTime()));
		timeLabel.setFont(Font.font("Dialog", FontWeight.BOLD, 34));
		panel.add(timeLabel, 0, 0);

		Scene scene = new Scene(panel, 240, 100);
		stage.setTitle("Digital Watch");
		stage.setScene(scene);
		stage.setResizable(false);
    }

    private void startWatch() {
    	Timeline timer = new Timeline(new KeyFrame(Duration.millis(UPDATE_INTERVAL), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				time = Calendar.getInstance(Locale.JAPAN);
				timeLabel.setText(FORMAT.format(time.getTime()));
			}
		}));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

}
