package dc2_1.src;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class DigitalWatch extends JFrame {


	private WatchPanel watchPanel;

	public static void main(String[] args) {
		new DigitalWatch().startWatch();
	}

	public DigitalWatch() {
		setTitle("Watch");
		initialize();
	}

	private void initialize() {

		setSize(240, 100);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//時計表示
		watchPanel = new WatchPanel();
		add(watchPanel);
	}

	public void startWatch() {
		setVisible(true);
		watchPanel.updateTime();
	}

	private static class WatchPanel extends JPanel{

		private Calendar time = Calendar.getInstance(Locale.JAPAN);
		private final Format FORMAT = new SimpleDateFormat("HH:mm:ss");
		private final int UPDATE_INTERVAL = 1000;

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D timeGraphic = (Graphics2D) g;
			timeGraphic.drawString(FORMAT.format(time.getTime()), 50,50);
			setFont(new Font("Dialog",Font.BOLD,34));
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
	}


}
