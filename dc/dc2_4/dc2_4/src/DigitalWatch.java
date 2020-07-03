package dc2_4.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class DigitalWatch extends JFrame implements ActionListener {

	private WatchPanel watchPanel;

	public static void main(String[] args) {
		new DigitalWatch().startWatch();
	}

	public DigitalWatch() {
		setTitle("Watch");
		initialize();
	}

	private void initialize() {

		setSize(350, 160);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//時計表示
		watchPanel = new WatchPanel();
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

		System.out.println("test");
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
	}
}
