package src.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import src.trade.coin.CoinCheckClient;
import src.trade.coin.PARAM_KEY;
import src.trade.exec.TaskWorker;
import src.trade.manager.CoinManager;
import src.trade.util.CreateFileUtil;

public class HomeUI extends JFrame implements ActionListener {

	private enum Action{
		startStop,update
	}

	private static final Logger log = Logger.getLogger(HomeUI.class);

	private final long INTERVAL = 60000;
	private final long TIME_INTERVAL = 1000;
	private final long DUMP_INTERVAL = 86400000; //24 hours
	private final String ICON_PATH = ".\\Configuration\\icon\\icon.png";
	private boolean isStarted = false;
	private JButton startButton = null;
	private JLabel currentTime = null;
	private JLabel currentPrice = null;
	private Timer timer = new Timer();
	private Timer timeTimer = new Timer();
	private Timer dumpTimer = new Timer();

	private final TaskWorker taskWorker;
	private List<ITabComponent> tabInstanceList = new ArrayList<>();

	public HomeUI(){
		super();
		initialize();
		taskWorker = new TaskWorker();
	}

	private void initialize(){
		setSize(1000,600);
		setTitle("勝手に取引マン");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		ImageIcon icon = new ImageIcon(ICON_PATH);
		setIconImage(icon.getImage());

		setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		currentTime = new JLabel("現在時刻：" + getTimeStr());
		panel.add(currentTime);
		startUpdateTime();

		JSONObject current = new JSONObject(CoinCheckClient.getCurrentPrice());
		String price = current.getString(PARAM_KEY.rate.name());
		CoinManager.getInstance().setCurrentRate(price);
		currentPrice = new JLabel("現在価格 : " + price);
		panel.add(currentPrice);
		startUpdatePrice();

		startButton = new JButton("開始");
		startButton.addActionListener(this);
		startButton.setActionCommand(Action.startStop.name());
		panel.add(startButton);

		JButton updateButton = new JButton("更新");
		updateButton.addActionListener(this);
		updateButton.setActionCommand(Action.update.name());
		panel.add(updateButton);
		add("North", panel);

		//Contents tab, Insert contents to tabInstanceList.
		JTabbedPane tabPane = new JTabbedPane();
		tabInstanceList.add(new HistoryDispUI());
		tabInstanceList.add(new ExecTradeMethodUI());
		for (ITabComponent component: tabInstanceList) {
			tabPane.addTab(component.getTabName(), component.createPanel());
		}
		add("Center", tabPane);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals(Action.startStop.name())) {
			startButton.setText(isStarted ? "開始" : "終了");
			isStarted = !isStarted;
			startStopTrade(isStarted);
		} else if (command.equals(Action.update.name())) {
			currentPrice.setText("現在価格 : " + CoinManager.getInstance().getCurrentRate());
			for (ITabComponent component: tabInstanceList) {
				component.updateComponent();
			}
		}
	}

	private void startStopTrade(boolean isStarted){
		if (isStarted){
			taskWorker.startTask(INTERVAL);
			// dump price history file not to consume memory for price cache
			dumpData();
		} else {
			taskWorker.stopAllTask();
			dumpTimer.cancel();
		}
	}

	private void startUpdatePrice() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				currentPrice.setText("現在価格 : " + CoinManager.getInstance().getCurrentRate());
				for (ITabComponent component: tabInstanceList) {
					component.updateByConstantInterval();
				}
			}
		}, INTERVAL, INTERVAL);
	}

	private void startUpdateTime() {
		timeTimer = new Timer();
		timeTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				currentTime.setText("現在時刻：" + getTimeStr());
			}
		}, TIME_INTERVAL, TIME_INTERVAL);
	}

	private String getTimeStr() {
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd(E) hh:mm:ss");
		return sdf.format(cl.getTime());
	}

	private void dumpData(){
		dumpTimer = new Timer();
		Date startTime = new Date();
		startTime.setHours(23);
		startTime.setMinutes(59);
		dumpTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				log.info("Dump the price history.");
				CreateFileUtil.createPriceHistoryCSV();
				CoinManager.getInstance().clearHistory();
			}
		}, startTime, DUMP_INTERVAL);
	}

}
