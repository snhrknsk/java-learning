package dc2_2.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WatchPropertiesDialog extends JDialog implements ActionListener{

	private final DigitalWatch watch;
	private Color backColor = Color.GREEN;
	private Color strColor = Color.BLUE;
	private int fontSize = 34;
	private String fontName = "";
//	private Font font = new Font("Dialog",Font.BOLD,34);
	private final Font CONTENT_FONT =  new Font("Dialog",Font.PLAIN,12);
	private JComboBox<WatchSize> screenSizeSelector;
	private JComboBox<WatchColor> backColorSelector;
	private JComboBox<WatchColor> strColorSelector;
	private JComboBox<String> fontSelector;

	private enum Action{
		OK
	}

	private enum WatchSize{
		SMALL(34, 300,130),
		MIDDLE(60,350,160),
		LARGE(100,600,250);
		private int fontSize;
		private int width;
		private int height;
		private WatchSize(int size, int width, int height) {
			this.fontSize = size;
			this.width = width;
			this.height = height;
		}
		public int getSize() {
			return fontSize;
		}
		public int getHeight() {
			return height;
		}
	}

	private enum WatchColor{
		BLACK(Color.BLACK),
		GRAY(Color.GRAY),
		WHITE(Color.WHITE),
		GREEN(Color.GREEN),
		BLUE(Color.BLUE),
		RED(Color.RED),
		;

		private Color color;
		WatchColor(Color color){
			this.color = color;
		}
		public Color getColor() {
			return color;
		}

	}

	public WatchPropertiesDialog(DigitalWatch watch) {
		this.watch = watch;
		setLocation(watch.getLocation().x + 10, watch.getLocation().y + 10);
		initialize();

	}

	private void initialize() {

		setTitle("Property");
		setSize(300, 200);
		setFont(CONTENT_FONT);

		JPanel panel = new JPanel();
		GridBagLayout gbLayout = new GridBagLayout();
		panel.setLayout(gbLayout);
		Insets insets = new Insets(3, 5, 1, 1);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
		JLabel screenSize = new JLabel("Screen Size :");
		gbLayout.setConstraints(screenSize, gbc);
		panel.add(screenSize);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    gbc.gridwidth = 2;
	    gbc.gridheight = 1;
	    gbc.weightx = 10.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
	    screenSizeSelector = new JComboBox<>();
	    createSelector(screenSizeSelector, WatchSize.values());
	    screenSizeSelector.addActionListener(this);
		gbLayout.setConstraints(screenSizeSelector, gbc);
		panel.add(screenSizeSelector);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
		JLabel backColor = new JLabel("Back Color :");
		gbLayout.setConstraints(backColor, gbc);
		panel.add(backColor);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    gbc.gridwidth = 2;
	    gbc.gridheight = 1;
	    gbc.weightx = 10.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
		backColorSelector = new JComboBox<>();
		backColorSelector.addActionListener(this);
		createSelector(backColorSelector, WatchColor.values());
		gbLayout.setConstraints(backColorSelector, gbc);
		panel.add(backColorSelector);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
		JLabel fontName = new JLabel("Font :");
		gbLayout.setConstraints(fontName, gbc);
		panel.add(fontName);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    gbc.gridwidth = 2;
	    gbc.gridheight = 1;
	    gbc.weightx = 10.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
		fontSelector = new JComboBox<>();
		fontSelector.addActionListener(this);
		createFontSelector(fontSelector);
		gbLayout.setConstraints(fontSelector, gbc);
		panel.add(fontSelector);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
		JLabel fontColor = new JLabel("Font Color :");
		gbLayout.setConstraints(fontColor, gbc);
		panel.add(fontColor);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 1;
	    gbc.gridy = 3;
	    gbc.gridwidth = 2;
	    gbc.gridheight = 1;
	    gbc.weightx = 10.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
		strColorSelector = new JComboBox<>();
		strColorSelector.addActionListener(this);
		createSelector(strColorSelector, WatchColor.values());
		gbLayout.setConstraints(strColorSelector, gbc);
		panel.add(strColorSelector);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 1;
	    gbc.gridy = 4;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 0.5;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
		JButton button = new JButton("OK");
		button.setActionCommand(Action.OK.name());
		button.addActionListener(this);
		gbLayout.setConstraints(button, gbc);
		panel.add(button);

		add(panel);
		setVisible(true);
	}

	private void createFontSelector(JComboBox<String> choice) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font[] fonts = ge.getAllFonts();
//		choice.addItem(currentStrFont.getFontName());
		Set<String> fontSet = new HashSet<>();
//		fontSet.add(currentStrFont.getFontName());
		for (Font font : fonts) {
			if (!fontSet.contains(font.getFontName())) {
				choice.addItem(font.getFontName());
				fontSet.add(font.getFontName());
			}

		}
	}

	/**
	 *
	 * @param selector
	 * @param elements
	 * 必ずSelectorのEnumの値を入れる<br>
	 * 型がSelectorと異なる場合ClassCastException
	 */
	@SuppressWarnings("unchecked")
	private <E> void createSelector(JComboBox<E> selector, Object[] elements) {
		for (Object element : elements) {
			selector.addItem((E)element);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals(Action.OK.name())) {
			fontSize = screenSizeSelector.getItemAt(screenSizeSelector.getSelectedIndex()).getSize();
			backColor = backColorSelector.getItemAt(backColorSelector.getSelectedIndex()).getColor();
			strColor = strColorSelector.getItemAt(strColorSelector.getSelectedIndex()).getColor();
			fontName = fontSelector.getItemAt(fontSelector.getSelectedIndex());
			Font font = new Font(fontName, Font.BOLD, fontSize);
			Dimension dim = getMaxScreenDimension(font);
			watch.setNewProperties(backColor, strColor, font, dim);
			dispose();
		}
	}

	private Dimension getMaxScreenDimension(Font font) {
		FontMetrics fontMetrics;
		fontMetrics = this.getFontMetrics(font);
		return new Dimension(maxNumWidth(fontMetrics), screenSizeSelector.getItemAt(screenSizeSelector.getSelectedIndex()).getHeight());
	}

	/**
     * フォントからHH:MM:SSの最大文字列長を求める
     * @param fontMetrics
     * @return
     */
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
