package src;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * デジタル時計を表示するクラス
 */
public class DigitalWatch extends Frame {

	private Calendar time = Calendar.getInstance(Locale.JAPAN);
	private final Format FORMAT = new SimpleDateFormat("HH:mm:ss");
	private final int UPDATE_INTERVAL = 1000;

	public static void main(String[] args) {
		Frame window = new DigitalWatch();
		window.setVisible(true);
	}

    public DigitalWatch() {
    	super("時計");
		setSize(240, 100);
		setFont(new Font("Dialog",Font.BOLD,34));
		addWindowListener(new WatchWindowAdapter());
		setResizable(false);
    }

    /**
     * 時刻を表示し、時刻を更新する
     */
    @Override
    public void paint(Graphics g) {

    	g.drawString(FORMAT.format(time.getTime()), 50,70);

    	Timer timer = new Timer();
		TimerTask task = new UpdateTimer();
		timer.schedule(task, UPDATE_INTERVAL);
    }

    /**
     * 定期的に時計を更新するための{@link TimerTask}処理を持つ{@link DegitalWatch}の内部クラス<br>
     *
     */
    private class UpdateTimer extends TimerTask{

		@Override
		public void run() {
			time = Calendar.getInstance(Locale.JAPAN);
			repaint();
		}
    }

    /**
     * Windowの×ボタン対応
     */
    private static class WatchWindowAdapter extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
           System.exit(0);
        }
    }
}



