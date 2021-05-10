package src.trade.exec;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import src.trade.manager.TaskManager;
import src.trade.util.CreateFileUtil;

/**
 * Exec defined tasks in {@link TaskManager} regularly
 */
public class TaskWorker {

	private static Logger log = Logger.getLogger(TaskWorker.class);
	private Timer timer = null;

	/**
	 * Edit this constructor for exec trade logic<br>
	 * Add initial exec logic class to {@link TaskManager#getTradingTask()} to exec them.<br>
	 * After {@link #startTask(long)}, they are executed for constant interval.
	 */
	public TaskWorker(){
	}

	public void startTask(long interval){

		if (interval <= 0){
			log.error("Interval is invalid : " + interval);
			throw new IllegalArgumentException();
		}
		log.info("Start Trade. Plugin = " + TaskManager.getInstance().getTradingTask());
		taskExec();
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				taskExec();
			}
		}, interval, interval);
	}

	private void taskExec() {
		log.info("Exec tasks." + TaskManager.getInstance().getTradingTask());
		try {
			for (ITradeLogic task : TaskManager.getInstance().getTradingTask()) {
				task.exec();
			}
		}catch (Exception e){
			log.error(e);
		}
	}

	public void stopAllTask(){
		timer.cancel();
		for (ITradeLogic task : TaskManager.getInstance().getTradingTask() ) {
			task.stopTask();
		}
		log.info("End Trade");
		postProcess();
	}

	public void postProcess(){

		// out put coin price history
		CreateFileUtil.createPriceHistoryCSV();
		CreateFileUtil.createTradeHistory();
		CreateFileUtil.createOpenOrder();

	}
}
