package dc3_4.src;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.prefs.Preferences;

import com.sun.javafx.geom.Dimension2D;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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

	private static Preferences pref = Preferences.userRoot().node("digitalWatchFx");
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
	private enum SavedKey{WINDOW_TOP, WINDOW_LEFT, WINDOW_HEIGHT, WINDOW_WIDTH, FONT, FONT_SIZE, BACK_COLOR, FONT_COLOR}

	public static void main(String[] args) {
	    launch(args);
	}

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		loadPref();
		createComponent(stage);
		startWatch();
		stage.show();
	}

	@Override
	public void stop(){
		//end process
		pref.putDouble(SavedKey.WINDOW_TOP.name(), stage.getY());
		pref.putDouble(SavedKey.WINDOW_LEFT.name(), stage.getX());
		pref.putInt(SavedKey.WINDOW_WIDTH.name(), (int)property.getSize().width);
		pref.putInt(SavedKey.WINDOW_HEIGHT.name(), (int)property.getSize().height);
		pref.putInt(SavedKey.BACK_COLOR.name(), colorToInt(property.getBackColor()));
		pref.putInt(SavedKey.FONT_COLOR.name(), colorToInt(property.getFontColor()));
		pref.putInt(SavedKey.FONT_SIZE.name(), property.getFontSize());
		pref.put(SavedKey.FONT.name(), property.getFont());
	}

	private void loadPref() {
		int fontColor = pref.getInt(SavedKey.FONT_COLOR.name(), colorToInt(Color.BLACK));
		int backColor = pref.getInt(SavedKey.BACK_COLOR.name(), colorToInt(Color.GRAY));
		property = new Settings(pref.get(SavedKey.FONT.name(),
				Font.getDefault().getFamily()),
				pref.getInt(SavedKey.FONT_SIZE.name(), 34),
				new Dimension2D(pref.getInt(SavedKey.WINDOW_WIDTH.name(), 240),
						pref.getInt(SavedKey.WINDOW_HEIGHT.name(), 110)),
				intToColor(fontColor),
				intToColor(backColor));
		posiotion = new Dimension2D((float)pref.getDouble(SavedKey.WINDOW_LEFT.name(), 0), (float)pref.getDouble(SavedKey.WINDOW_TOP.name(), 0));
	}

	private void createComponent(Stage stage) {

		root = new VBox(2.0);
		menu = new MenuBar();
		Menu settingMenu = new Menu("メニュー");
		MenuItem propertyMenu = new MenuItem("プロパティ");
		propertyMenu.setOnAction(event -> showPropertyDialog(stage));
		settingMenu.getItems().add(propertyMenu);
		MenuItem exitMenu = new MenuItem("閉じる");
		exitMenu.setOnAction(e -> stage.hide());
		settingMenu.getItems().add(exitMenu);
		menu.getMenus().addAll(settingMenu);
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

		scene = new Scene(root, property.getSize().width, property.getSize().height);
		stage.setX(posiotion.width);
		stage.setY(posiotion.height);
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

	private int colorToInt(Color c) {
		int r = (int) Math.round(c.getRed() * 255);
		int g = (int) Math.round(c.getGreen() * 255);
		int b = (int) Math.round(c.getBlue() * 255);
		return (255 << 24) | (r << 16) | (g << 8) | b;
	}

	private Color intToColor(int value) {
		int r = (value >>> 16) & 0xFF;
		int g = (value >>> 8) & 0xFF;
		int b = value & 0xFF;
		return Color.rgb(r,g,b);
	}

}
