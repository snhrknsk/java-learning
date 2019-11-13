package dc.src;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;

import dc.src.DigitalWatch.MenuItems;

public class WatchPropertyDialog extends Dialog {

	private final Font CONTENT_FONT =  new Font("Dialog",Font.PLAIN,12);
	private final Font CONTENT_FONT_BOLD =  new Font("Dialog",Font.BOLD,14);
	private final LayoutManager FLOW_LAYOUT = new FlowLayout(FlowLayout.LEFT);

	private final DigitalWatch didigitalWatch;
	private final Color currentBackColor;
	private final Color currentStrColor;
	private final Font currentStrFont;

	//property value
	private TextField rBackValue;
	private TextField gBackValue;
	private TextField bBackValue;
	private TextField rFontValue;
	private TextField gFontValue;
	private TextField bFontValue;
	private Choice fontChoice;
	private TextField fontSizeField;

	public WatchPropertyDialog(DigitalWatch digitalWatch) {
		super(digitalWatch, MenuItems.Property.name(), true);
		this.didigitalWatch = digitalWatch;
//		dialog = new Dialog(this, MenuItems.Property.name(), true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
	        	dispose();
	        }
		});

		currentBackColor = digitalWatch.getBackColor();
		currentStrColor = digitalWatch.getStrColor();
		currentStrFont = digitalWatch.getTimerFont();
		initDialog();

	}

	/**
	 * ダイアログの各コンポーネントを作成する
	 */
	private void initDialog() {
        setSize(360 , 190);
        setLocation(didigitalWatch.getLocation());
        setResizable(false);
        setFont(CONTENT_FONT);
        setLayout(new GridLayout(5,1));
//        maxFontSize = cariculateMaxFontSize(currentStrFont);

        //BackColor
        Panel backColorPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        Label backColor = new Label("背景色 　　:");
        backColor.setFont(CONTENT_FONT_BOLD);
        backColorPanel.add(backColor);
        Label rBackLabel = new Label("R");
        backColorPanel.add(rBackLabel);
        rBackValue = new TextField(String.valueOf(currentBackColor.getRed()), 3);
        backColorPanel.add(rBackValue);
        Label gBackLabel = new Label("G");
        backColorPanel.add(gBackLabel);
        gBackValue = new TextField(String.valueOf(currentBackColor.getGreen()), 3);
        backColorPanel.add(gBackValue);
        Label bBackLabel = new Label("B");
        backColorPanel.add(bBackLabel);
        bBackValue = new TextField(String.valueOf(currentBackColor.getBlue()), 3);
        backColorPanel.add(bBackValue);
        add(backColorPanel);

        //Font
        Panel fontPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        Label fontLabel = new Label("フォント 　:");
        fontLabel.setFont(CONTENT_FONT_BOLD);
        fontPanel.add(fontLabel);
        fontChoice = new Choice();
        createFontChoice(fontChoice);
        fontChoice.setSize(new Dimension(10, 50));
        fontPanel.add(fontChoice);
        add(fontPanel);
        //TODO:fontChoice.getSelectedItem();

        //Font size
        Panel fontSizePanel = new Panel(FLOW_LAYOUT);
        Label fontSize = new Label("文字サイズ :");
        fontSize.setFont(CONTENT_FONT_BOLD);
        fontSizePanel.add(fontSize);
        fontSizeField = new TextField(String.valueOf(currentStrFont.getSize()), 20);
        fontSizePanel.add(fontSizeField);
        add(fontSizePanel);

        //FontColor
        Panel colorFontPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        Label fontColor = new Label("文字色　　 :");
        fontColor.setFont(CONTENT_FONT_BOLD);
        colorFontPanel.add(fontColor);

        Label rFontLabel = new Label("R");
        colorFontPanel.add(rFontLabel);
        rFontValue = new TextField(String.valueOf(currentStrColor.getRed()), 3);
        colorFontPanel.add(rFontValue);
        Label gFontLabel = new Label("G");
        colorFontPanel.add(gFontLabel);
        gFontValue = new TextField(String.valueOf(currentStrColor.getGreen()), 3);
        colorFontPanel.add(gFontValue);
        Label bFontLabel = new Label("B");
        colorFontPanel.add(bFontLabel);
        bFontValue = new TextField(String.valueOf(currentStrColor.getBlue()), 3);
        colorFontPanel.add(bFontValue);
        add(colorFontPanel);

        //OK Button
        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.RIGHT));
        FontButton button = new FontButton();
        buttonPanel.add(button);
        add(buttonPanel);

        setVisible(true);
	}

	private void createFontChoice(Choice choice) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font[] fonts = ge.getAllFonts();
		choice.addItem(currentStrFont.getFontName());
		Set<String> fontSet = new HashSet<>();
		for (Font font : fonts) {
			if (!fontSet.contains(font.getFontName())) {
				choice.addItem(font.getFontName());
				fontSet.add(font.getFontName());
			}

		}
	}

	/**
	 * プロパティの設定を時計に反映するためのボタン<br>
	 * 設定された値が正しいかの検証を行い反映する
	 */
	private class FontButton extends Button{
    	public FontButton() {
			super("　　OK　　");
			setFont(CONTENT_FONT_BOLD);
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Color backColor = validateColorValue(rBackValue.getText(), gBackValue.getText(), bBackValue.getText(), currentBackColor);
					Color fontColor = validateColorValue(rFontValue.getText(), gFontValue.getText(), bFontValue.getText(), currentStrColor);
					String fontName = fontChoice.getSelectedItem();
					Font font = new Font(fontName, Font.BOLD, 10);
					int fontSize = validateFontSize(fontSizeField.getText(), font);
					didigitalWatch.setWatchProperty(backColor, fontColor, new Font(fontName, Font.BOLD, fontSize));
					dispose();
				}
			});
		}
    }

	/**
	 * RGBが0~255に収まっているかチェック<br>
	 * 収まっていない場合元々の色を利用
	 * @param r
	 * @param g
	 * @param b
	 * @param defaultColor
	 * @return
	 */
	private Color validateColorValue(String r, String g, String b, Color defaultColor) {
		try {
			int intR = Integer.parseInt(r);
			int intG = Integer.parseInt(g);
			int intB = Integer.parseInt(b);
			if (intR >= 0 && intG >= 0 && intB >= 0 && intR < 256 && intG < 256 && intB < 256) {
				return new Color(intR, intG, intB);
			}
		}catch (NumberFormatException e) {
		}
		return defaultColor;
	}

	/**
	 * フォントサイズが最大ウィンドウサイズを超えていないかのチェック<br>
	 * 超えていたら最大値へ変更,-の場合は1へ変更
	 * @param fontSize
	 * @param font
	 * @return 最大フォントサイズ
	 */
	private int validateFontSize(String fontSize, Font font) {
		try {
			int size = Integer.parseInt(fontSize);
			if (size < 1) {
				return 1;
			}
			int maxFontSize = cariculateMaxFontSize(font);
			if (size > maxFontSize) {
				return maxFontSize;
			}
			return size;
		}catch (NumberFormatException e) {
		}
		return currentStrFont.getSize();
	}

	private int cariculateMaxFontSize(Font font) {
		Dimension max = getToolkit().getScreenSize();
		int maxFontSize = 10;
		FontMetrics fontMetrics;
		for (int i = 10;; i+=1) {
			fontMetrics = getFontMetrics(new Font(font.getFontName(),Font.BOLD,i));
			if (maxNumWidth(fontMetrics) > max.width) {
				break;
			}
			maxFontSize = i;
		}
		return maxFontSize;
	}

	private int maxNumWidth(FontMetrics fontMetrics) {
		char[] num = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		int max = 0;
		for (char c : num) {
			int width = fontMetrics.charWidth(c);
			if (max < width) {
				max = width;
			}
		}
		int width = max * 8 + fontMetrics.charWidth(':') * 2;
		if (width < 150) {
			return 150;
		}
		return width;
	}

}
