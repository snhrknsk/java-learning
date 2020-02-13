package dc;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dc.src.DigitalWatch.MenuItems;

public class WatchPropertyDialog extends Dialog {

	private final Font CONTENT_FONT =  new Font("Dialog",Font.PLAIN,12);
	private final Font CONTENT_FONT_BOLD =  new Font("Dialog",Font.BOLD,14);

	private Map<String, Color> colorMap = new LinkedHashMap<String, Color>() {
		{
			put("Black", Color.black);
			put("White", Color.white);
			put("Gray", Color.gray);
			put("Red", Color.red);
			put("Green", Color.green);
			put("Blue", Color.blue);
			put("Yellow", Color.yellow);
		}
	};
	private List<Integer> fontSizeList = new ArrayList<>(Arrays.asList(30,50, 100,150,200));

	private enum ColorType{FONT, BACK_GROUND};

	private final DigitalWatch didigitalWatch;
	private final Color currentBackColor;
	private final Color currentStrColor;
	private final Font currentStrFont;
	private final Integer currentFontSize;

	//property value
	private Choice fontChoice;
	private Choice fontColorChoice;
	private Choice fontSizeChoice;
	private Choice backColorChoice;

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
		currentFontSize = currentStrFont.getSize();
		initDialog();

	}

	/**
	 * ダイアログの各コンポーネントを作成する
	 */
	private void initDialog() {
        setLocation(didigitalWatch.getLocation());
        setResizable(false);
        setFont(CONTENT_FONT);

        // Layout
 		GridBagLayout layout = new GridBagLayout();
 		setLayout(layout);
 		setSize(400, 180);
 		setResizable(false);
 		GridBagConstraints constraints = new GridBagConstraints();
 		constraints.weighty = 0.1;

 		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.EAST;
		Label backColor = new Label("背景色");
		layout.setConstraints(backColor, constraints);
		add(backColor);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.WEST;
		backColorChoice = new Choice();
		createColorChoice(backColorChoice, ColorType.BACK_GROUND);
		layout.setConstraints(backColorChoice, constraints);
		add(backColorChoice);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.EAST;
		Label fontLabel = new Label("フォント");
		layout.setConstraints(fontLabel, constraints);
		add(fontLabel);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.WEST;
		fontChoice = new Choice();
		createFontChoice(fontChoice);
		layout.setConstraints(fontChoice, constraints);
		add(fontChoice);

		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.anchor = GridBagConstraints.EAST;
		Label fontColor = new Label("文字色");
		layout.setConstraints(fontColor, constraints);
		add(fontColor);
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.anchor = GridBagConstraints.WEST;
		fontColorChoice = new Choice();
		createColorChoice(fontColorChoice,ColorType.FONT);
		layout.setConstraints(fontColorChoice, constraints);
		add(fontColorChoice);

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.EAST;
		Label fontSize = new Label("文字サイズ");
		layout.setConstraints(fontSize, constraints);
		add(fontSize);
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.WEST;
		fontSizeChoice = new Choice();
		createSizeChoice(fontSizeChoice);
		layout.setConstraints(fontSizeChoice, constraints);
		add(fontSizeChoice);

		Panel buttonPanel = new Panel();
		buttonPanel.setLayout(new GridLayout(1, 2));
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(buttonPanel, constraints);
		add(buttonPanel);
		buttonPanel.add(new OKButton());
		buttonPanel.add(new CancelButton());

        setVisible(true);
	}

	private void createColorChoice(Choice choice, ColorType type) {

		String currentColor = null;
		switch (type) {
		case FONT:
			currentColor = getColorString(currentStrColor);
			break;
		case BACK_GROUND:
			currentColor = getColorString(currentBackColor);
		default:
			break;
		}
		choice.add(currentColor);
		for (String color : colorMap.keySet()) {
			if (color.equals(currentColor)) {
				continue;
			}
			choice.add(color.toString());
		}
	}

	private void createFontChoice(Choice choice) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font[] fonts = ge.getAllFonts();
		choice.addItem(currentStrFont.getFontName());
		Set<String> fontSet = new HashSet<>();
		fontSet.add(currentStrFont.getFontName());
		for (Font font : fonts) {
			if (!fontSet.contains(font.getFontName())) {
				choice.addItem(font.getFontName());
				fontSet.add(font.getFontName());
			}

		}
	}

	private void createSizeChoice(Choice choice) {
		choice.add(currentFontSize.toString());
		for (Integer size : fontSizeList) {
			if (size == currentFontSize) {
				continue;
			}
			choice.add(size.toString());
		}
	}

	private String getColorString(Color color) {
		for (String colorValue : colorMap.keySet()) {
			if (color.equals(colorMap.get(colorValue))) {
				return colorValue;
			}
		}
		return null;
	}

	/**
	 * プロパティの設定を時計に反映するためのボタン<br>
	 * 設定された値が正しいかの検証を行い反映する
	 */
	private class OKButton extends Button{
    	public OKButton() {
			super("　　OK　　");
			setFont(CONTENT_FONT_BOLD);
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Color backColor = colorMap.get(backColorChoice.getSelectedItem());
					Color fontColor = colorMap.get(fontColorChoice.getSelectedItem());
					String fontName = fontChoice.getSelectedItem();
					int fontSize = Integer.valueOf(fontSizeChoice.getSelectedItem());
					didigitalWatch.setWatchProperty(backColor, fontColor, new Font(fontName, Font.BOLD, fontSize));
					dispose();
				}
			});
		}
    }

	/**
	 * プロパティダイアログで設定された値へ変更せずに第zログを閉じる
	 *
	 */
	private class CancelButton extends Button{
    	public CancelButton() {
			super("　CANCEL　");
			setFont(CONTENT_FONT_BOLD);
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
    }

}
