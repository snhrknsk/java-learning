package dc.src;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DigitalWatch extends Frame implements ActionListener {

	private Calendar time = Calendar.getInstance(Locale.JAPAN);
	private final Format FORMAT = new SimpleDateFormat("HH:mm:ss");
	private final int UPDATE_INTERVAL = 1000;
	private Image buf;
	private Graphics ct;
	private Dimension dim;
	private Color backColor = Color.gray;
	private Color strColor = Color.black;
	private Font timerFont = new Font("Dialog",Font.BOLD,34);
	protected final static Font MENU_FONT = new Font("Dialog",Font.PLAIN,12);
	public enum MenuItems{Property,};
	int count = 0;

	public static void main(String[] args) {
		DigitalWatch window = new DigitalWatch();
		window.init();
		window.setVisible(true);
//		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.execUpdate();
	}

	public void init() {
		this.setMenuBar(createMenue());
		dim = getSize();
		buf = createImage(dim.width , dim.height);
	}

    public DigitalWatch() {
    	super("時計");
		setSize(240, 140);
		setFont(timerFont);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
		    }
		});
    }

    /**
     * 時刻を表示し、時刻を更新する
     */
    @Override
    public synchronized void paint(Graphics g) {

    	FontMetrics fontMetrics;
		String timeStr = FORMAT.format(time.getTime());
		fontMetrics = this.getFontMetrics(timerFont);
		buf = createImage(fontMetrics.stringWidth(timeStr)+100, fontMetrics.getAscent()+100);
		ct = buf.getGraphics();
		ct.setColor(strColor);
		ct.setFont(timerFont);
		ct.drawString(timeStr, 50,fontMetrics.getAscent() + 75 - fontMetrics.getDescent());

		//フォントサイズに合わせてWindowをリサイズ
    	setSize(fontMetrics.stringWidth(timeStr) + 100, fontMetrics.getAscent() + 100 );
    	setBackground(backColor);
    	g.drawImage(buf, 0, 0, this);
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
				Thread.sleep(UPDATE_INTERVAL );
			} catch (InterruptedException e) {
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
			System.out.println("menu");
			new WatchPropertyDialog(this);
		}
	}

    public void setWatchProperty(Color backColor, Color strColor, Font font) {
		this.backColor = backColor;
		this.strColor = strColor;
		this.timerFont = font;
		repaint();
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
