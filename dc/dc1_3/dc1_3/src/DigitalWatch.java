package dc1_3.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class DigitalWatch extends Window implements ActionListener{

	private DigitalWatch watch = null;

	private PopupMenu pop = new PopupMenu();
	private Calendar time = Calendar.getInstance(Locale.JAPAN);
	private final Format FORMAT = new SimpleDateFormat("HH:mm:ss");
	private final int UPDATE_INTERVAL = 1000;
	private Image buf;
	private Graphics ct;
	private Dimension dim;
	private Color backColor = Color.gray;
	private Color strColor = Color.black;
	private Font timerFont = new Font("Dialog",Font.BOLD,70);
	private String fontName = "Dialog";
	private int fontSize = 70;
	private int fontLocationX = 0;
	private int fontLocationY = 0;
	private  Point startDrag, startPos;
	int count = 0;
	enum MenuSet{
		Exit, Font, Dialog, 游ゴシック, メイリオ, 明朝体,
		FontSize, Small, Medium, Large,
		FontColor, FWhite, FBlack, FGray, FRed, FBlue, FGreen,
		BackColor, BWhite, BBlack, BGray, BRed, BBlue, BGreen;
	}

	public DigitalWatch(Frame owner) {
		super(owner);
		watch = this;
		init();
	}

	public static void main(String[] args) {

		Frame frame = new Frame();
		new DigitalWatch(frame);

	}

	public void init() {
		dim = new Dimension();
		dim.setSize(380, 140);
		buf = createImage(dim.width , dim.height);
		setFontLocation();

		addMouseListener( getMouseListener() );
		addMouseMotionListener( getMouseMotionListener() );

		createPopMenu(pop);
		watch.add(pop);
		watch.setLayout(new FlowLayout(FlowLayout.CENTER));
		watch.setSize(dim);
		watch.setVisible(true);
		watch.execUpdate();
	}

	Point getScreenLocation(MouseEvent e) {
	    Point p1 = e.getPoint();
	    Point p2 = watch.getLocationOnScreen();
	    return new Point(p1.x+p2.x, p1.y+p2.y);
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
    	toFront();
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

    @Override
    public void update(Graphics g) {
		paint(g);
	}
    /**
     * ポップアップメニュー<br>
     * フォントサイズ、フォントカラー、背景色、フォントの設定と時計を閉じるためのメニューを作成
     */
    private void createPopMenu(PopupMenu pop) {

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
				    startPos = watch.getLocation();
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
			    watch.setLocation(startPos.x+xdiff, startPos.y+ydiff);
			}
		};
    }

    /**
     * 文字列の横幅とスクリーンサイズから文字列配置位置を計算し画面中央に文字を置く
     */
    private void setFontLocation() {
    	String timeStr = FORMAT.format(time.getTime());
		FontMetrics fm = this.getFontMetrics(timerFont);
		Rectangle rectText = fm.getStringBounds(timeStr, ct).getBounds();
		fontLocationX=(int) ((dim.getWidth() - rectText.width)/2);
		fontLocationY=(int) (( dim.height -rectText.height)/2 + fm.getMaxAscent() );
    }

    /**
     * ポップアップメニューの操作に対する処理
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals(MenuSet.Exit.name())) {
        	watch.dispose();
        	System.exit(0);
        	return;
		} else if (command.equals(MenuSet.Small.name())){
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
		}

		timerFont =  new Font(fontName,Font.BOLD,fontSize);
		setSize((int)dim.getWidth(), dim.height);
		setFontLocation();

		repaint();
	}
}
