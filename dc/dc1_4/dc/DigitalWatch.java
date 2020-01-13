package dc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
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

public class DigitalWatch extends Frame implements ActionListener {

	private static DigitalWatch window;
	private Calendar time = Calendar.getInstance(Locale.JAPAN);
	private final Format FORMAT = new SimpleDateFormat("HH:mm:ss");
	private final int UPDATE_INTERVAL = 1000;
	private Image buf;
	private Graphics ct;
	private Dimension dim;
	private Color backColor = Color.gray;
	private Color strColor = Color.black;
	private Font timerFont = new Font("Dialog",Font.BOLD,50);
	protected final static Font MENU_FONT = new Font("Dialog",Font.PLAIN,12);
	private int fontLocationX = 0;
	private int fontLocationY = 0;
	private enum MenuItems{Property,};
	private enum SavedKey{WINDOW_TOP, WINDOW_LEFT, FONT, FONT_SIZE, BACK_COLOR, FONT_COLOR}
	int count = 0;
	private static Preferences pref = Preferences.userRoot().node("digital_watch");

	public static void main(String[] args) {
		window = new DigitalWatch();
		window.setLocation(pref.getInt(SavedKey.WINDOW_LEFT.name(), 0), pref.getInt(SavedKey.WINDOW_TOP.name(), 0));
		window.setVisible(true);
		window.setResizable(false);
		window.execUpdate();
	}

	public void init() {
		this.setMenuBar(createMenue());
		dim = new Dimension();
		timerFont = new Font(pref.get(SavedKey.FONT.name(), "Dialog"), Font.BOLD, pref.getInt(SavedKey.FONT_SIZE.name(), 50));
		strColor = new Color(pref.getInt(SavedKey.FONT_COLOR.name(), Color.black.getRGB()));
		backColor = new Color(pref.getInt(SavedKey.BACK_COLOR.name(), Color.gray.getRGB()));
		setMaxScreenDimension(timerFont);
		buf = createImage(dim.width , dim.height);
		setFontLocation();
	}

    public DigitalWatch() {
    	super("時計");
		init();
		setSize((int)dim.getWidth(), dim.height);
		setFont(timerFont);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				pref.put(SavedKey.FONT.name(), timerFont.getFontName());
				pref.putInt(SavedKey.BACK_COLOR.name(), backColor.getRGB());
				pref.putInt(SavedKey.FONT_COLOR.name(), strColor.getRGB());
				pref.putInt(SavedKey.FONT_SIZE.name(), timerFont.getSize());
				pref.putInt(SavedKey.WINDOW_LEFT.name(), window.getLocation().x);
				pref.putInt(SavedKey.WINDOW_TOP.name(), window.getLocation().y);
				dispose();
				System.exit(0);
		    }
		});
    }

    /**
     * 時刻を表示し、時刻を更新する
     */
    @Override
    public void paint(Graphics g) {

		String timeStr = FORMAT.format(time.getTime());

		buf = createImage(dim.width, dim.height);
		ct = buf.getGraphics();
		ct.setColor(backColor);
		ct.fillRect(0 , 0 , dim.width , dim.height);
		ct.setColor(strColor);
		ct.setFont(timerFont);
		ct.drawString(timeStr, fontLocationX,fontLocationY);

    	g.drawImage(buf, 0, 0, this);
    }

    public void update(Graphics g) {
		paint(g);
	}

	/**
     * 定期的に時計を更新する<br>
     *
     */
    public void execUpdate() {
    	while( true ) {
        	time = Calendar.getInstance(Locale.JAPAN);
        	repaint();
        	try {
				Thread.sleep( UPDATE_INTERVAL );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }

    private MenuBar createMenue() {
    	MenuBar menuBar = new MenuBar();
    	Menu menu = new Menu("Menu");
    	menu.setFont(MENU_FONT);
    	MenuItem propatyMenu = new MenuItem(MenuItems.Property.name());
    	propatyMenu.setFont(MENU_FONT);
    	propatyMenu.addActionListener(this);
    	menu.add(propatyMenu);
    	menuBar.add(menu);
    	return menuBar;
    }

    /**
     * メニューが押された際の処理<br>
     * プロパティダイアログ
     */
    public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == MenuItems.Property.name()) {
			new WatchPropertyDialog(this);
		}
	}

    public void setWatchProperty(Color backColor, Color strColor, Font font) {
		this.backColor = backColor;
		this.strColor = strColor;
		this.timerFont = font;
		setMaxScreenDimension(font);
		setSize((int)dim.getWidth(), dim.height);
		setFontLocation();
		repaint();
	}

    private void setMaxScreenDimension(Font font) {
    	FontMetrics fontMetrics;
		fontMetrics = this.getFontMetrics(timerFont);
		dim.setSize(maxNumWidth(fontMetrics), validateMaxHeight(fontMetrics));
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

    /**
     * 最小サイズは縦130とする
     * @param fontMetrics
     * @return
     */
    private int validateMaxHeight(FontMetrics fontMetrics) {
    	if (fontMetrics.getHeight() * 1.5 < 130) {
			return 130;
		}
    	return (int) (fontMetrics.getHeight() * 1.5);
    }

    /**
     * 文字列の横幅とスクリーンサイズから文字列配置位置を計算し画面中央に文字を置く
     */
    private void setFontLocation() {
    	String timeStr = FORMAT.format(time.getTime());
		FontMetrics fm = this.getFontMetrics(timerFont);
		Rectangle rectText = fm.getStringBounds(timeStr, ct).getBounds();
		fontLocationX=(int) ((dim.getWidth() - rectText.width)/2);
		fontLocationY=(int) (( dim.height -rectText.height)/2 + fm.getMaxAscent() + 20);
    }

    public Color getBackColor() {
		return backColor;
	}

	public Color getStrColor() {
		return strColor;
	}

	public Font getTimerFont() {
		return timerFont;
	}




}
