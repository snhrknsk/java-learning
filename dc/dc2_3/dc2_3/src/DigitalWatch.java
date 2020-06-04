package dc2_3.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JPanel;
import javax.swing.JWindow;


public class DigitalWatch extends JWindow implements ActionListener {

	enum MenuSet{
		Exit,
		Font, Dialog, 游ゴシック, メイリオ, 明朝体,
		FontSize, Small, Medium, Large,
		FontColor, FWhite, FBlack, FGray, FRed, FBlue, FGreen,
		BackColor, BWhite, BBlack, BGray, BRed, BBlue, BGreen;
	}

	private WatchPanel watchPanel;
	private  Point startDrag, startPos;
	private PopupMenu pop = new PopupMenu();
	private DigitalWatch watch;

	public static void main(String[] args) {
		new DigitalWatch().startWatch();
	}

	public DigitalWatch() {
		initialize();
	}

	private void initialize() {

		watch = this;
		setSize(350, 160);
		//時計表示
		watchPanel = new WatchPanel();
		add(watchPanel);
		addMouseListener( getMouseListener() );
		addMouseMotionListener( getMouseMotionListener() );
		createMenu();
		add(pop);
	}

	private void createMenu() {
		//Font
    	Menu fontMenu = new Menu(MenuSet.Font.name());
    	MenuItem itemFont1 = new MenuItem(MenuSet.Dialog.name());
    	itemFont1.addActionListener(watch);
    	fontMenu.add(itemFont1);
    	MenuItem itemFont2 = new MenuItem(MenuSet.メイリオ.name());
    	itemFont2.addActionListener(watch);
    	fontMenu.add(itemFont2);
    	MenuItem itemFont3 = new MenuItem(MenuSet.明朝体.name());
    	itemFont3.addActionListener(watch);
    	fontMenu.add(itemFont3);
    	MenuItem itemFont4 = new MenuItem(MenuSet.游ゴシック.name());
    	itemFont4.addActionListener(watch);
    	fontMenu.add(itemFont4);
    	pop.add(fontMenu);

    	//FontSize
    	Menu fontSizeMenu = new Menu(MenuSet.FontSize.name());
    	MenuItem itemFontSmall = new MenuItem(MenuSet.Small.name());
    	itemFontSmall.addActionListener(watch);
    	fontSizeMenu.add(itemFontSmall);
    	MenuItem itemFontMedium = new MenuItem(MenuSet.Medium.name());
    	itemFontMedium.addActionListener(watch);
    	fontSizeMenu.add(itemFontMedium);
    	MenuItem itemFontLarge = new MenuItem(MenuSet.Large.name());
    	itemFontLarge.addActionListener(watch);
    	fontSizeMenu.add(itemFontLarge);
    	pop.add(fontSizeMenu);
    	//FontColor
    	Menu fontColorMenu = new Menu(MenuSet.FontColor.name());
    	MenuItem itemFontWhite = new MenuItem("Whtie");
    	itemFontWhite.setActionCommand(MenuSet.FWhite.name());
    	itemFontWhite.addActionListener(watch);
    	fontColorMenu.add(itemFontWhite);
    	MenuItem itemFontBlack  = new MenuItem("Black");
    	itemFontBlack.setActionCommand(MenuSet.FBlack.name());
    	itemFontBlack.addActionListener(watch);
    	fontColorMenu.add(itemFontBlack);
    	MenuItem itemFontGray = new MenuItem("Gray");
    	itemFontGray.setActionCommand(MenuSet.FGray.name());
    	itemFontGray.addActionListener(watch);
    	fontColorMenu.add(itemFontGray);
    	MenuItem itemFontRed  = new MenuItem("Red");
    	itemFontRed.setActionCommand(MenuSet.FRed.name());
    	itemFontRed.addActionListener(watch);
    	fontColorMenu.add(itemFontRed);
    	MenuItem itemFontBlue = new MenuItem("Blue");
    	itemFontBlue.setActionCommand(MenuSet.FBlue.name());
    	itemFontBlue.addActionListener(watch);
    	fontColorMenu.add(itemFontBlue);
    	MenuItem itemFontGreen = new MenuItem("Green");
    	itemFontGreen.setActionCommand(MenuSet.FGreen.name());
    	itemFontGreen.addActionListener(watch);
    	fontColorMenu.add(itemFontGreen);
    	pop.add(fontColorMenu);
    	//Back Color
    	Menu backColorMenu = new Menu(MenuSet.BackColor.name());
    	MenuItem itemBackWhite = new MenuItem("White");
    	itemBackWhite.setActionCommand(MenuSet.BWhite.name());
    	itemBackWhite.addActionListener(watch);
    	backColorMenu.add(itemBackWhite);
    	MenuItem itemBackBlack  = new MenuItem("Black");
    	itemBackBlack.setActionCommand(MenuSet.BBlack.name());
    	itemBackBlack.addActionListener(watch);
    	backColorMenu.add(itemBackBlack);
    	MenuItem itemBackGray = new MenuItem("Gray");
    	itemBackGray.setActionCommand(MenuSet.BGray.name());
    	itemBackGray.addActionListener(watch);
    	backColorMenu.add(itemBackGray);
    	MenuItem itemBackRed  = new MenuItem("Red");
    	itemBackRed.setActionCommand(MenuSet.BRed.name());
    	itemBackRed.addActionListener(watch);
    	backColorMenu.add(itemBackRed);
    	MenuItem itemBackBlue = new MenuItem("Blue");
    	itemBackBlue.setActionCommand(MenuSet.BBlue.name());
    	itemBackBlue.addActionListener(watch);
    	backColorMenu.add(itemBackBlue);
    	MenuItem itemBackGreen = new MenuItem("Green");
    	itemBackGreen.setActionCommand(MenuSet.BGreen.name());
    	itemBackGreen.addActionListener(watch);
    	backColorMenu.add(itemBackGreen);
    	pop.add(backColorMenu);
    	pop.addSeparator();
    	//Exit
    	MenuItem itemExit = new MenuItem(MenuSet.Exit.name());
    	itemExit.addActionListener(watch);
    	pop.add(itemExit);

	}

	public void startWatch() {
		setVisible(true);
		watchPanel.updateTime();
	}

	public WatchPanel getTimePanel() {
		return watchPanel;
	}

	public void setNewProperties(Color backColor, Color fontColor, Font font, Dimension dim) {
		setSize(dim);
		watchPanel.setNewPanel(backColor, fontColor, font, dim);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String fontName = watchPanel.getFont().getName();
		int fontSize = watchPanel.getFont().getSize();
		Dimension dim = watchPanel.getDimension();
		Color strColor = watchPanel.getfontColor();
		Color backColor = watchPanel.getBackColor();
		String command = e.getActionCommand();
		if (command.equals(MenuSet.Small.name())){
			fontSize = 30;
			dim.setSize(180, 70);
        } else if (command.equals(MenuSet.Medium.name())){
        	fontSize = 70;
        	dim.setSize(380, 140);
        } else if (command.equals(MenuSet.Large.name())){
        	fontSize = 130;
        	dim.setSize(690, 250);
        } else if (command.equals(MenuSet.FWhite.name())) {
        	strColor = Color.WHITE;
		} else if (command.equals(MenuSet.FBlack.name())) {
        	strColor = Color.BLACK;
		} else if (command.equals(MenuSet.FGray.name())) {
        	strColor = Color.GRAY;
		} else if (command.equals(MenuSet.FRed.name())) {
        	strColor = Color.RED;
		} else if (command.equals(MenuSet.FBlue.name())) {
        	strColor = Color.BLUE;
		} else if (command.equals(MenuSet.FGreen.name())) {
        	strColor = Color.GREEN;
		} else if (command.equals(MenuSet.BWhite.name())) {
        	backColor = Color.WHITE;
		} else if (command.equals(MenuSet.BBlack.name())) {
        	backColor = Color.BLACK;
		} else if (command.equals(MenuSet.BGray.name())) {
        	backColor = Color.GRAY;
		} else if (command.equals(MenuSet.BRed.name())) {
        	backColor = Color.RED;
		} else if (command.equals(MenuSet.BBlue.name())) {
        	backColor = Color.BLUE;
		} else if (command.equals(MenuSet.BGreen.name())) {
        	backColor = Color.GREEN;
		} else if (command.equals(MenuSet.Dialog.name())) {
			fontName = MenuSet.Dialog.name();
		} else if (command.equals(MenuSet.メイリオ.name())) {
			fontName = MenuSet.メイリオ.name();
		} else if (command.equals(MenuSet.明朝体.name())) {
			fontName = MenuSet.明朝体.name();
		} else if (command.equals(MenuSet.游ゴシック.name())) {
			fontName = MenuSet.游ゴシック.name();
		} else if (command.equals(MenuSet.Exit.name())) {
			watch.dispose();
			System.exit(0);
			return;
		}
		setNewProperties(backColor, strColor, new Font(fontName, Font.BOLD, fontSize), dim);
	}

	/**
	 * 時間表示のパネルクラス
	 */
	static class WatchPanel extends JPanel{

		private Calendar time = Calendar.getInstance(Locale.JAPAN);
		private final Format FORMAT = new SimpleDateFormat("HH:mm:ss");
		private final int UPDATE_INTERVAL = 1000;
		private Color backColor = Color.GRAY;
		private Color strColor = Color.BLACK;
		private Dimension location = new Dimension(50, 60);
		private Font font = new Font("Dialog",Font.BOLD,60);
		private Dimension screenSize = new Dimension(350, 130);

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

		public void setNewPanel(Color backColor, Color fontColor, Font font, Dimension dim) {
			this.backColor = backColor;
			this.strColor = fontColor;
			this.font = font;
			this.screenSize = dim;
			repaint();
		}

		public Color getBackColor() {
			return backColor;
		}

		public Color getfontColor() {
			return strColor;
		}

		public Font getFont() {
			return font;
		}

		public Dimension getDimension() {
			return screenSize;
		}

	    /**
	     * 文字列の横幅とスクリーンサイズから文字列配置位置を計算し画面中央に文字を置く
	     */
		private void setFontLocation(Graphics ct) {
	    	String timeStr = FORMAT.format(time.getTime());
			FontMetrics fm = this.getFontMetrics(font);
			Rectangle rectText = fm.getStringBounds(timeStr, ct).getBounds();
			location.width =(int) ((screenSize.getWidth() - rectText.width)/2);
			location.height=(ct.getClipBounds().height-rectText.height)/2 + fm.getMaxAscent();
	    }
	}

	 /**
     * マウスリスナー
     */
    private MouseListener getMouseListener(){
    	return new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if ( MouseEvent.BUTTON1 == e.getButton() ) {
					startDrag = getScreenLocation(e);
				    startPos = getLocation();
				} else if ( MouseEvent.BUTTON3 == e.getButton() ) {
					pop.show(watch , e.getX() , e.getY());
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		};
    }

	Point getScreenLocation(MouseEvent e) {
	    Point p1 = e.getPoint();
	    Point p2 = watch.getLocationOnScreen();
	    return new Point(p1.x+p2.x, p1.y+p2.y);
	}

	private MouseMotionListener getMouseMotionListener() {
		return new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				Point cursor = getScreenLocation(e);
			    int xdiff = cursor.x - startDrag.x;
			    int ydiff = cursor.y - startDrag.y;
			    setLocation(startPos.x+xdiff, startPos.y+ydiff);
			}
		};
	}
}