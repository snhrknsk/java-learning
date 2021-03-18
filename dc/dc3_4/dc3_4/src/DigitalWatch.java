package dc3_4.src;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import dc3_4.src.config.ConfigUtil;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DigitalWatch extends Application{

	private Calendar time = Calendar.getInstance(Locale.JAPAN);
	private final Format FORMAT = new SimpleDateFormat("HH:mm:ss");
	private final int UPDATE_INTERVAL = 1000;
	private Dimension2D posiotion = new Dimension2D(0, 0);

	//Component
	private Stage stage;
	private Scene scene;
	private GridPane panel;
	private VBox root;
	private Label timeLabel;
	private MenuBar menu;

	private Settings property = null;//new Settings(Font.getDefault().getFamily(), 34, new Dimension2D(240, 120), Color.BLACK, Color.GRAY);

	public static void main(String[] args) {
	    launch(args);
	}

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		initialize();
		createComponent(stage);
		startWatch();
		stage.show();
	}

	@Override
	public void stop(){
		//end process
		ConfigUtil.PrefConfig.savePref(stage, property);
	}

	private void initialize() {
		stage.setTitle("Digital Watch");
		try {
			stage.getIcons().add(new Image(ConfigUtil.ICON_PATH));
		} catch (Exception e) {
			System.out.println(ConfigUtil.ICON_PATH);
			e.printStackTrace();
		}
		stage.setResizable(false);
		property = ConfigUtil.PrefConfig.loadPref();
		posiotion = property.getPosition();
		stage.setX(posiotion.getWidth());
		stage.setY(posiotion.getHeight());

		menu = new MenuBar();
		Menu settingMenu = new Menu("メニュー");
		MenuItem propertyMenu = new MenuItem("プロパティ");
		propertyMenu.setOnAction(event -> showPropertyDialog(stage));
		settingMenu.getItems().add(propertyMenu);
		MenuItem exitMenu = new MenuItem("閉じる");
		exitMenu.setOnAction(e -> stage.hide());
		settingMenu.getItems().add(exitMenu);
		menu.getMenus().addAll(settingMenu);

	}

	private void createComponent(Stage stage) {

		root = new VBox(2.0);
		root.getChildren().add(menu);
		root.setSpacing(20);

		panel = new GridPane();
		panel.setAlignment(Pos.CENTER);

		timeLabel = new Label(FORMAT.format(time.getTime()));
		timeLabel.setFont(Font.font(property.getFont(), FontWeight.BOLD, property.getFontSize()));
		timeLabel.setTextFill(property.getFontColor());
		panel.add(timeLabel, 0, 0);

		root.getChildren().add(panel);
		root.setBackground(new Background(new BackgroundFill(property.getBackColor(), CornerRadii.EMPTY, Insets.EMPTY)));

		scene = new Scene(root, property.getSize().getWidth(), property.getSize().getHeight());
		stage.setScene(scene);
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

	private void showPropertyDialog(Stage stage) {
		Stage propertyStage = new Stage();
		propertyStage.initModality(Modality.WINDOW_MODAL);
		propertyStage.initOwner(stage.getScene().getWindow());
		new WatchPropetyDialog(propertyStage, this);
	}

	public void setPropeties(Settings prop) {
		this.property = prop;
		createComponent(stage);
	}

	public Settings getPropeties() {
		return property;
	}

	public void getDragEventHandler(double x, double y) {

		System.out.println(x);
		if (x < 100) {
			timeLabel.setTextFill(Color.GREEN);
		} else {
			timeLabel.setTextFill(Color.RED);
		}

	}
}
