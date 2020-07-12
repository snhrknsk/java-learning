package dc2_4.src;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

public class WatchPropertiesDialog extends JDialog implements ActionListener{

	private final DigitalWatch watch;
	private final WatchProperties iniWatchProp;
	private WatchProperties newWatchProp;
	private final Font CONTENT_FONT =  new Font("Dialog",Font.PLAIN,12);
	private JComboBox<WatchSize> screenSizeSelector;
	private JComboBox<WatchColor> backColorSelector;
	private JComboBox<WatchColor> strColorSelector;
	private JComboBox<String> fontSelector;

	private enum Action{
		OK,CANCEL
	}

	private enum WatchSize{
		SMALL(34, 300,140),
		MIDDLE(60,350,170),
		LARGE(100,600,270);
		private int fontSize;
		private int height;
		private WatchSize(int size, int width, int height) {
			this.fontSize = size;
			this.height = height;
		}
		public int getSize() {
			return fontSize;
		}
		public int getHeight() {
			return height;
		}
		public static WatchSize getSizeObject(int fontSize) {
			for (WatchSize element : WatchSize.values()) {
				if (element.getSize() == fontSize) {
					return element;
				}
			}
			return MIDDLE;
		}
	}

	private enum WatchColor{
		BLACK(Color.BLACK),
		GRAY(Color.GRAY),
		WHITE(Color.WHITE),
		GREEN(Color.GREEN),
		BLUE(Color.BLUE),
		RED(Color.RED),;
		private Color color;
		WatchColor(Color color){
			this.color = color;
		}
		public Color getColor() {
			return color;
		}
		public static WatchColor getWatchColor(Color color) {
			for (WatchColor watchColor : WatchColor.values()) {
				if (watchColor.getColor().equals(color)) {
					return watchColor;
				}
			}
			return BLACK;
		}
	}

	public WatchPropertiesDialog(DigitalWatch watch) {
		this.watch = watch;
		iniWatchProp = watch.getWatchProperties().getCurrentProperties();
		newWatchProp = new WatchProperties(iniWatchProp);
		setLocation(watch.getLocation().x + 10, watch.getLocation().y + 10);
		initialize();

	}

	private void initialize() {

		setTitle("Property");
		setSize(400, 200);
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
	    gbc.anchor = GridBagConstraints.EAST;
		JLabel screenSize = new JLabel("Screen Size :");
		screenSize.setHorizontalAlignment(SwingConstants.RIGHT);
		gbLayout.setConstraints(screenSize, gbc);
		panel.add(screenSize);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    gbc.gridwidth = 3;
	    gbc.gridheight = 1;
	    gbc.weightx = 10.0;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
	    screenSizeSelector = new JComboBox<>();
	    createSelector(screenSizeSelector, WatchSize.values(), WatchSize.getSizeObject((int)iniWatchProp.getFont().getSize()));
	    screenSizeSelector.addActionListener(this);
	    screenSizeSelector.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED){
					if (screenSizeSelector.getSelectedItem() instanceof WatchSize) {
						WatchSize selectedSize = (WatchSize)screenSizeSelector.getSelectedItem();
						if (selectedSize.getSize() !=(newWatchProp.getFont().getSize())) {
							Font newFont = new Font(iniWatchProp.getFont().getFontName(), Font.BOLD, selectedSize.getSize());
							newWatchProp.setFont(newFont);
							Dimension dim = getMaxScreenDimension(newFont);
							newWatchProp.setSize(dim);
							watch.setNewProperties(newWatchProp);
						}
					}
				}
			}
		});
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
	    gbc.anchor = GridBagConstraints.EAST;
		JLabel backColor = new JLabel("Back Color :");
		backColor.setHorizontalAlignment(SwingConstants.RIGHT);
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
		createSelector(backColorSelector, WatchColor.values(), WatchColor.getWatchColor(iniWatchProp.getBackColor()));
		backColorSelector.setRenderer(new MyCellRenderer());
		backColorSelector.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED){
					if (backColorSelector.getSelectedItem() instanceof WatchColor) {
						WatchColor selectedBackColor = (WatchColor)backColorSelector.getSelectedItem();
						if (!selectedBackColor.getColor().equals(newWatchProp.getBackColor())) {
							newWatchProp.setBackColor(selectedBackColor.getColor());
							watch.setNewProperties(newWatchProp);
						}
					}
				}
			}
		});
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
	    gbc.anchor = GridBagConstraints.EAST;
		JLabel fontName = new JLabel("Font :");
		fontName.setHorizontalAlignment(SwingConstants.RIGHT);
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
		fontSelector.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED){
					String selectedFontName = (String)fontSelector.getSelectedItem();
					if (!selectedFontName.equals(iniWatchProp.getFont().getFontName())) {
						Font newFont = new Font(selectedFontName, Font.BOLD, newWatchProp.getFont().getSize());
						newWatchProp.setFont(newFont);
						newWatchProp.setSize(getMaxScreenDimension(newFont));
						watch.setNewProperties(newWatchProp);
					}
				}
			}
		});
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
	    gbc.anchor = GridBagConstraints.EAST;
		JLabel fontColor = new JLabel("Font Color :");
		fontColor.setHorizontalAlignment(SwingConstants.RIGHT);
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
		createSelector(strColorSelector, WatchColor.values(), WatchColor.getWatchColor(iniWatchProp.getFontColor()));
		strColorSelector.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED){
					if (strColorSelector.getSelectedItem() instanceof WatchColor) {
						WatchColor selectedStrColor = (WatchColor)strColorSelector.getSelectedItem();
						if (!selectedStrColor.getColor().equals(newWatchProp.getFontColor())) {
							newWatchProp.setFontColor(selectedStrColor.getColor());
							watch.setNewProperties(newWatchProp);
						}
					}
				}
			}
		});
		strColorSelector.setRenderer(new MyCellRenderer());
		gbLayout.setConstraints(strColorSelector, gbc);
		panel.add(strColorSelector);

		JPanel buttonPanel = new JPanel();
		FlowLayout buttonLayout = new FlowLayout();
		buttonLayout.setAlignment(FlowLayout.RIGHT);
		buttonPanel.setLayout(buttonLayout);

		JButton button = new JButton("   OK   ");
		button.setActionCommand(Action.OK.name());
		button.addActionListener(this);
		buttonPanel.add(button);

		JButton cancelButton = new JButton("CANCEL");
		cancelButton.setActionCommand(Action.CANCEL.name());
		cancelButton.addActionListener(this);
		buttonPanel.add(cancelButton);

		gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 1;
	    gbc.gridy = 4;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.weightx = 0.5;
	    gbc.weighty = 1.0;
	    gbc.insets = insets;
	    gbLayout.setConstraints(buttonPanel, gbc);
		panel.add(buttonPanel);

		add(panel);
		setResizable(false);
		setVisible(true);

	}

	private void createFontSelector(JComboBox<String> choice) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font[] fonts = ge.getAllFonts();
		Set<String> fontSet = new HashSet<>();
		fontSet.add(iniWatchProp.getFont().getFontName());
		choice.addItem(iniWatchProp.getFont().getFontName());
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
	private <E> void createSelector(JComboBox<E> selector, Object[] elements, Object ini) {
		selector.addItem((E)ini);
		for (Object element : elements) {
			if (!element.equals(ini)) {
				selector.addItem((E)element);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals(Action.OK.name())) {
			watch.setNewProperties(newWatchProp);
			dispose();
		} else if (command.equals(Action.CANCEL.name())) {
			watch.setNewProperties(iniWatchProp);
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

    class MyCellRenderer extends JLabel implements ListCellRenderer<WatchColor>{

		MyCellRenderer(){
		  setOpaque(true);
		}
		public Component getListCellRendererComponent(JList<? extends WatchColor> list, WatchColor value, int index,
				boolean isSelected, boolean cellHasFocus) {
			WatchColor data = (WatchColor)value;
			setText(data.name());
			BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
			image.setRGB(0, 0, data.getColor().getRGB());
			for (int y = 0; y < 10; y++) {
				for (int x = 0; x < 10; x++) {
					image.setRGB(x, y, data.getColor().getRGB());
				}
			}
			setIcon(new ImageIcon(image));
			return this;
		}
	}
}

