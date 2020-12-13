package dc3_3.src;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

import com.sun.javafx.geom.Dimension2D;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class DigitalWatch extends Application{

	private Calendar time = Calendar.getInstance(Locale.JAPAN);
	private final Format FORMAT = new SimpleDateFormat("HH:mm:ss");
	private final int UPDATE_INTERVAL = 1000;
	private List<MenuItem> fontMenuItemList = new ArrayList<>();

	public enum FontSize{
		SMALL(34, 240, 50), MIDDLE(60, 350, 90), LARGE(100, 600, 150),XLARGE(250, 1300, 400);
		private int width;
		private int height;
		private int fontSize;
		private FontSize(int fontSize, int width, int height) {
			this.fontSize = fontSize;
			this.width = width;
			this.height = height;
		}
		public int getFontSize() {
			return fontSize;
		}
		public int getWidth() {
			return width;
		}
		public int getHeight() {
			return height;
		}
	}

	private enum Colors {
		Black(Color.BLACK),
		Gray(Color.GRAY),
		Green(Color.GREEN),
		Blue(Color.BLUE),
		Red(Color.RED),
		Yellow(Color.YELLOW),
		White(Color.WHITE),;
		private Color color;
		private Colors(Color color) {
			this.color = color;
		}
		public Color getColor() {
			return color;
		}
		public static Colors getColors(String colorName) {
			for (Colors c : Colors.values()) {
				if (colorName.equals(c.name())) {
					return c;
				}
			}
			return Colors.Black;
		}
	}

	//Component
	private Stage stage;
	private Scene scene;
	private GridPane panel;
	private VBox root;
	private Label timeLabel;
	private ContextMenu menu = new ContextMenu();;

	private double xOffset = 0;
	private double yOffset = 0;

	private Settings property = new Settings(Font.getDefault().getFamily(), 34, new Dimension2D(240, 50), Color.BLACK, Color.GRAY);

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
	}

	public static void main(String[] args) {
	    launch(args);
	}

	private void initialize() {
		Menu fontMenu = new Menu("フォント");
		List<String> fontFamilyList = Font.getFamilies();
		for (String font : fontFamilyList) {
			MenuItem fontMenuItem = new MenuItem(font);
			fontMenuItem.addEventHandler(ActionEvent.ACTION, e -> updateFont(font));
			fontMenuItemList.add(fontMenuItem);
		}
		fontMenu.getItems().addAll(fontMenuItemList);
		Menu fontSizeMenu = new Menu("フォントサイズ");
		MenuItem smallMenuItem = new MenuItem(FontSize.SMALL.name());
		smallMenuItem.addEventHandler(ActionEvent.ACTION, e -> updateFontSize(FontSize.SMALL));
		MenuItem middleMenuItem = new MenuItem(FontSize.MIDDLE.name());
		middleMenuItem.addEventHandler(ActionEvent.ACTION, e -> updateFontSize(FontSize.MIDDLE));
		MenuItem largeMenuItem = new MenuItem(FontSize.LARGE.name());
		largeMenuItem.addEventHandler(ActionEvent.ACTION, e -> updateFontSize(FontSize.LARGE));
		MenuItem xlargeMenuItem = new MenuItem(FontSize.XLARGE.name());
		xlargeMenuItem.addEventHandler(ActionEvent.ACTION,	e -> updateFontSize(FontSize.XLARGE));
		fontSizeMenu.getItems().addAll(smallMenuItem, middleMenuItem, largeMenuItem, xlargeMenuItem);
		Menu fontColorMenu = new Menu("フォントカラー");
		createColorMenu(fontColorMenu, (c) -> updateFontColor(c));
		Menu backColorMenu = new Menu("背景色");
		createColorMenu(backColorMenu, (c) -> updateBackColor(c));
		MenuItem exitMenu = new MenuItem("閉じる");
		exitMenu.addEventHandler(ActionEvent.ACTION, e -> stage.hide());
		menu.getItems().addAll(fontMenu, fontSizeMenu, fontColorMenu, backColorMenu, exitMenu);

		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setTitle("Digital Watch");
		stage.setResizable(false);

	}

	private void createComponent(Stage stage) {

		root = new VBox(2.0);

		panel = new GridPane();
		panel.setAlignment(Pos.BOTTOM_CENTER);

		timeLabel = new Label(FORMAT.format(time.getTime()));
		timeLabel.setFont(Font.font(property.getFont(), FontWeight.BOLD, property.getFontSize()));
		timeLabel.setTextFill(property.getFontColor());
		panel.add(timeLabel, 0, 0);
		root.getChildren().add(panel);

		root.setBackground(new Background(new BackgroundFill(property.getBackColor(), CornerRadii.EMPTY, Insets.EMPTY)));

		scene = new Scene(root, computeTextWidth(Font.font(property.getFont(), FontWeight.BOLD, property.getFontSize()), FORMAT.format(time.getTime()), Integer.MAX_VALUE) * 1.3, Font.font(property.getFont(), FontWeight.BOLD, property.getFontSize()).getSize() * 1.3);
		stage.setScene(scene);
		//マウス・ボタンが押されたとき
		root.setOnMousePressed(event -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();
		});
		//マウス・ボタンがドラッグされるとき
		root.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() - xOffset);
			stage.setY(event.getScreenY() - yOffset);
		});
        scene.setOnMouseClicked(this::mouseClicked);
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

	private void createColorMenu(Menu menu, Consumer<Color> f) {
		for (Colors color : Colors.values()) {
			MenuItem menuItem = new MenuItem(color.name());
			menuItem.addEventHandler(ActionEvent.ACTION, e -> f.accept(Colors.getColors(color.name()).getColor()));
			menu.getItems().add(menuItem);
		}
	}

	private void updateFont(String font) {
		property.setFont(font);
		createComponent(stage);
	}

	private void updateFontSize(FontSize fontSize) {
		property.setFontSize(fontSize.getFontSize());
		property.setSize(new Dimension2D(fontSize.getWidth(), fontSize.getHeight()));
		createComponent(stage);
	}

	private void updateFontColor(Color c) {
		property.setFontColor(c);
		createComponent(stage);
	}

	private void updateBackColor(Color c) {
		property.setBackColor(c);
		createComponent(stage);
	}

	private double computeTextWidth(Font font, String text, double wrappingWidth) {
		Text helper = new Text();
		helper.setFont(font);
		helper.setText(text);
		// Note that the wrapping width needs to be set to zero before
		// getting the text"s real preferred width.
		helper.setWrappingWidth(0);
		helper.setLineSpacing(0);
		double w = Math.min(helper.prefWidth(-1), wrappingWidth);
		helper.setWrappingWidth((int)Math.ceil(w));
		double textWidth = Math.ceil(helper.getLayoutBounds().getWidth());
		return textWidth;
	}

	private void mouseClicked(MouseEvent e) {

		switch(e.getButton()) {
		case SECONDARY:
			menu.show(root, stage.getX() + e.getX(), stage.getY() + e.getY());
			break;
		default:
			break;
		}
	}

}
