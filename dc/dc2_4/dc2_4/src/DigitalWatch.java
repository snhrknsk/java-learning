package dc2_4.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class DigitalWatch extends JFrame implements ActionListener {

	private WatchPanel watchPanel;
	private static Preferences pref = Preferences.userRoot().node("digitalWatch");
	private enum SavedKey{WINDOW_TOP, WINDOW_LEFT, WINDOW_HEIGHT, WINDOW_WIDTH, FONT, FONT_SIZE, BACK_COLOR, FONT_COLOR}

	public static void main(String[] args) {
		new DigitalWatch().startWatch();
	}

	public DigitalWatch() {
		setTitle("Watch");
		ImageIcon icon = new ImageIcon(".\\configuration\\icon\\icon.png");
	    setIconImage(icon.getImage());
		initialize();
	}

	private void initialize() {

		setSize(pref.getInt(SavedKey.WINDOW_WIDTH.name(), 350), pref.getInt(SavedKey.WINDOW_HEIGHT.name(), 170));
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new WindowClosing());
		setLocation(pref.getInt(SavedKey.WINDOW_LEFT.name(), 0),pref.getInt(SavedKey.WINDOW_TOP.name(), 0));
		//時計表示
		watchPanel = new WatchPanel(loadLastProp());
		add(watchPanel);
		setJMenuBar(createMenuBar());
	}

	private JMenuBar createMenuBar() {
		JMenuBar jMenu = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem item = new JMenuItem("Property");
		item.setActionCommand("Property");
		item.addActionListener(this);
		menu.add(item);
		jMenu.add(menu);
		return jMenu;
	}

	private WatchProperties loadLastProp() {
		Color backColor = new Color(pref.getInt(SavedKey.BACK_COLOR.name(), Color.GRAY.getRGB()));
		Color fontColor = new Color(pref.getInt(SavedKey.FONT_COLOR.name(), Color.black.getRGB()));
		String fontName = pref.get(SavedKey.FONT.name(), "Dialog");
		int fontSize = pref.getInt(SavedKey.FONT_SIZE.name(), 60);
		Dimension size = new Dimension(pref.getInt(SavedKey.WINDOW_WIDTH.name(), 350), pref.getInt(SavedKey.WINDOW_HEIGHT.name(), 170));
		WatchProperties prop = new WatchProperties(backColor, fontColor, new Font(fontName, Font.BOLD, fontSize), size);
		return prop;
	}

	/**
	 * 時計を起動する
	 */
	public void startWatch() {
		setVisible(true);
		watchPanel.updateTime();
	}

	public WatchPanel getTimePanel() {
		return watchPanel;
	}

	public void setNewProperties(WatchProperties prop) {
		setSize(prop.getSize());
		watchPanel.setNewPanel(prop);
	}

	public WatchPanel getWatchProperties() {
		return watchPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		if (command.equals("Property")) {
			new WatchPropertiesDialog(this);
		}
	}

	/**
	 * 時間表示のパネルクラス
	 */
	static class WatchPanel extends JPanel{

		private Calendar time = Calendar.getInstance(Locale.JAPAN);
		private final Format FORMAT = new SimpleDateFormat("HH:mm:ss");
		private final int UPDATE_INTERVAL = 1000;
		private Color backColor;// = Color.GRAY;
		private Color strColor;// = Color.BLACK;
		private Dimension location = new Dimension(50, 60);
		private Font font;// = new Font("Dialog",Font.BOLD,60);
		private Dimension screenSize;// = new Dimension(350, 170);

		public WatchPanel(WatchProperties initialProp) {
			this.backColor = initialProp.getBackColor();
			this.strColor = initialProp.getFontColor();
			this.font = initialProp.getFont();
			this.screenSize = initialProp.getSize();
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(backColor);
			Graphics2D timeGraphic = (Graphics2D) g;
			timeGraphic.setColor(strColor);
			setFont(font);
			setFontLocation(timeGraphic);
			timeGraphic.drawString(FORMAT.format(time.getTime()), location.width, location.height);
		}

		/**
		 * 呼び出しは一度のみ<vr>
		 * UPDATE_INTERVAL間隔で表示を更新する
		 */
		public void updateTime() {
			while(true) {
				time = Calendar.getInstance(Locale.JAPAN);
				repaint();
				try {
					Thread.sleep(UPDATE_INTERVAL);
				} catch (InterruptedException e) {
				}
			}
		}

		public void setNewPanel(WatchProperties prop) {
			this.backColor = prop.getBackColor();
			this.strColor = prop.getFontColor();
			this.font = prop.getFont();
			this.screenSize = prop.getSize();
			repaint();
		}

	    /**
	     * 文字列の横幅とスクリーンサイズから文字列配置位置を計算し画面中央に文字を置く
	     */
		private void setFontLocation(Graphics ct) {
	    	String timeStr = FORMAT.format(time.getTime());
			FontMetrics fm = this.getFontMetrics(font);
			Rectangle rectText = fm.getStringBounds(timeStr, ct).getBounds();
			location.width =(int) ((screenSize.getWidth() - rectText.width)/2);
			location.height=ct.getClipBounds().height-rectText.height/2;
	    }

		public WatchProperties getCurrentProperties() {
			WatchProperties prop = new WatchProperties(backColor, strColor, font, screenSize);
			return prop;
		}
	}

	/**
	 * 終了時に設定を保存する
	 */
	private class WindowClosing extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			WatchProperties prop = getWatchProperties().getCurrentProperties();
			Point point = getLocation();
			pref.putInt(SavedKey.WINDOW_TOP.name(), point.y);
			pref.putInt(SavedKey.WINDOW_LEFT.name(), point.x);
			pref.putInt(SavedKey.WINDOW_WIDTH.name(), prop.getSize().width);
			pref.putInt(SavedKey.WINDOW_HEIGHT.name(), prop.getSize().height);
			pref.putInt(SavedKey.BACK_COLOR.name(), prop.getBackColor().getRGB());
			pref.putInt(SavedKey.FONT_COLOR.name(), prop.getFontColor().getRGB());
			pref.putInt(SavedKey.FONT_SIZE.name(), prop.getFont().getSize());
			pref.put(SavedKey.FONT.name(), prop.getFont().getFontName());
        }
    }
}
