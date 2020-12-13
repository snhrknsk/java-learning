package dc3_4.src;

import java.awt.image.BufferedImage;
import java.util.List;

import com.sun.javafx.geom.Dimension2D;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class WatchPropetyDialog {

	private final Stage stage;
	private final DigitalWatch watch;
	private final Settings currentProp;
	private ChoiceBox<String> fontChoice;
	private ChoiceBox<FontSize> fontSizeChoice;
	private ComboBox<Colors> fontColorChoice;
	private ComboBox<Colors> backColorChoice;

	public enum FontSize{
		SMALL(34, 240, 110), MIDDLE(60, 350, 160), LARGE(100, 600, 210),XLARGE(250, 1100, 400);
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
		White(Color.WHITE),
		;
		private Color color;
		private Colors(Color color) {
			this.color = color;
		}
		public Color getColor() {
			return color;
		}
	}

	public WatchPropetyDialog(Stage stage, DigitalWatch watch) {
		this.stage = stage;
		this.watch = watch;
		this.currentProp = watch.getPropeties();
		initialize();
	}

	private void initialize() {
		VBox root = new VBox(2.0);
    	GridPane panel = new GridPane();
    	panel.setAlignment(Pos.CENTER);
    	panel.setHgap(2.0);
    	panel.setVgap(4.0);

		Label fontLabel = new Label("フォント : ");
		panel.add(fontLabel, 0, 0);
		fontChoice = new ChoiceBox<>();
		createFontChoice(fontChoice);
		panel.add(fontChoice, 1, 0);

		Label fontSizeLabel = new Label("フォントサイズ : ");
		panel.add(fontSizeLabel, 0, 1);
		fontSizeChoice = new ChoiceBox<>();
		createFontSizeChoice(fontSizeChoice);
		panel.add(fontSizeChoice, 1, 1);

		Label fontColorLabel = new Label("フォントカラー : ");
		panel.add(fontColorLabel, 0, 2);
		fontColorChoice = new ComboBox<>();
		createColorChoice(fontColorChoice, currentProp.getFontColor());
		panel.add(fontColorChoice, 1, 2);

//		ColorPicker fontColorPicker = new ColorPicker();
//		panel.add(fontColorPicker, 1, 2);

		Label backColor = new Label("背景色 : ");
		panel.add(backColor, 0, 3);
		backColorChoice = new ComboBox<>();
		createColorChoice(backColorChoice, currentProp.getBackColor());
		panel.add(backColorChoice, 1, 3);

		HBox buttonBox = new HBox(2.0);
		Button okButton = new Button("  OK  ");
		okButton.setOnAction(e -> updateAsNewPropety());
		Button cancelButton= new Button("CANCEL");
		cancelButton.setOnAction(e -> stage.hide());
		buttonBox.getChildren().addAll(okButton, cancelButton);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);

		root.getChildren().addAll(panel, buttonBox);

		Scene scene = new Scene(root);
		stage.setTitle("Digital Watch");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	private void updateAsNewPropety() {
		String font = fontChoice.getValue();
		FontSize choiceFontSize = fontSizeChoice.getValue();
		int fontSize = choiceFontSize.getFontSize();
		Dimension2D size = new Dimension2D(choiceFontSize.getWidth(), choiceFontSize.getHeight());
		Color fontColor = fontColorChoice.getValue().getColor();
		Color backColor = backColorChoice.getValue().getColor();
		Settings prop = new Settings(font, fontSize, size, fontColor, backColor);
		watch.setPropeties(prop);
		stage.hide();
	}

	private void createFontChoice(ChoiceBox<String> choice) {
		List<String> fontFamilyList = Font.getFamilies();
		for (String font : fontFamilyList) {
			if (currentProp.getFont().equals(font)) {
				choice.setValue(font);
			}
			choice.getItems().add(font);
		}
	}

	private void createFontSizeChoice(ChoiceBox<FontSize> choice) {
		for (FontSize size : FontSize.values()) {
			if (currentProp.getFontSize() == size.getFontSize()) {
				choice.setValue(size);
			}
			choice.getItems().add(size);
		}
	}

	private void createColorChoice(ComboBox<Colors> choice, Color currentColor) {
		for (Colors color : Colors.values()) {
			if (currentColor.equals(color.getColor())) {
				choice.setValue(color);
			}
			choice.getItems().add(color);
		}
		createColorChip(choice);
	}

	private void createColorChip(ComboBox<Colors> combo) {
		Callback<ListView<Colors>,ListCell<Colors>> cellFactory = p -> {
			ListCell<Colors> cell = new ListCell<Colors>(){

				@Override
				public void updateItem( Colors item , boolean empty ){
					// 元関数を呼び出し
					super.updateItem(item, empty);

					// Null対策
					if( item == null ){ return; }

					// セルのプロパティを設定
					// テキスト
					setText(item.toString());

					BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
					for (int y = 0; y < 16; y++) {
						for (int x = 0; x < 16; x++) {
							if (y == 0 || x == 0 || y == 15 || x == 15) {
								image.setRGB(x, y, colorToInt(Color.BLACK));
							} else {
								image.setRGB(x, y, colorToInt(item.getColor()));
							}
						}
					}
					WritableImage chipImage = SwingFXUtils.toFXImage(image, null);

					ImageView   imgView = new ImageView( chipImage );
					imgView.setFitWidth( 16 );
					imgView.setFitHeight( 16 );
					setGraphic( imgView );
				}
			};
			return cell;
		};
		combo.setCellFactory( cellFactory );
	}

	private int colorToInt(Color c) {
		int r = (int) Math.round(c.getRed() * 255);
		int g = (int) Math.round(c.getGreen() * 255);
		int b = (int) Math.round(c.getBlue() * 255);
		return (255 << 24) | (r << 16) | (g << 8) | b;
	}
}