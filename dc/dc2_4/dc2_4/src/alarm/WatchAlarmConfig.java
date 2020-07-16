package dc2_4.src.alarm;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

import dc2_4.src.config.ConfigUtil;
import dc2_4.src.config.GlobalConfig;

public class WatchAlarmConfig {

	private static WatchAlarmConfig instance = null;
	private Map<String, AlarmData> dataMap = new HashMap<>();
	private Map<String, Timer> timers = new HashMap<>();

	private static final long MILLI_OF_DAY = 1000 * 60 * 60 * 24;

	private WatchAlarmConfig() {
	}

	public static synchronized WatchAlarmConfig getInstance() {
		if (instance == null) {
			instance = new WatchAlarmConfig();
		}
		return instance;
	}

	public Map<String, AlarmData> getAlarmData(){
		return dataMap;
	}

	public void addAlarmConf(AlarmData data, String identifier ) {
		dataMap.put(identifier, data);
		setAlarm(data, identifier);
	}

	public boolean existAlarmName(String name) {
		return dataMap.containsKey(name);
	}

	public void deleteAlarm(String identifier) {
		setAlarmEnable(false, identifier);
		dataMap.remove(identifier);
	}

	public void setAlarmEnable(boolean enable, String identifier) {
		AlarmData data = dataMap.get(identifier);
		if (data.isOn() == enable) {
			return;
		}

		if (enable) {
			setAlarm(data, identifier);
			data.setOn(true);
		} else {
			if (timers.get(identifier) == null) {
				System.out.println("Cancel the timer " + identifier + ". But not found timer");
			} else {
				System.out.println(identifier + " is canceled.");
				data.setOn(false);
				timers.get(identifier).cancel();
				timers.get(identifier).purge();
				timers.remove(identifier);
			}
		}
	}

	private void setAlarm(AlarmData data, String identifier) {
		Calendar date = Calendar.getInstance();
		Timer timer;
		if (date.get(Calendar.HOUR_OF_DAY) < data.getHour()) {
			timer = createTimer(date, data, false);
		} else if (date.get(Calendar.HOUR_OF_DAY) == data.getHour()) {
			if (date.get(Calendar.MINUTE) < data.getMinute()) {
				timer = createTimer(date, data, false);
			} else {
				timer = createTimer(date, data, true);
			}
		} else {
			timer = createTimer(date, data, true);
		}
		timers.put(identifier, timer);
	}

	@SuppressWarnings("deprecation")
	private Timer createTimer(Calendar date, AlarmData data, boolean add1Day) {
		Timer timer = new Timer(true);

		if (add1Day) {
			date.add(Calendar.DAY_OF_MONTH, 1);
		}
		Date setDate = new Date(date.getTimeInMillis());
		setDate.setHours(data.getHour());
		setDate.setMinutes(data.getMinute());
		setDate.setSeconds(0);
		System.out.println("Set timer : " + setDate);
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				//java fx使えるのであればmp3も再生できるこちらを使う
//				AudioClip c = new AudioClip(new File(data.getSoundFilePath()).toURI().toString());
//				c.play();
//				//永遠になり続けてしまう・・・
//				JOptionPane.showMessageDialog(GlobalConfig.getOwnerFrame(), "アラームを止める", "アラーム", JOptionPane.ERROR_MESSAGE);
//				c.stop();
				try (AudioInputStream audioInputStream  = AudioSystem.getAudioInputStream(new File(data.getSoundFilePath()))){
					try(Clip clip = AudioSystem.getClip()){
				        clip.open(audioInputStream);
				        clip.start();
				        JOptionPane.showMessageDialog(GlobalConfig.getOwnerFrame(), "アラームを止める", "アラーム", JOptionPane.ERROR_MESSAGE);
						clip.stop();
					}
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
					System.out.println("Fail to sound alarm. " + e.getMessage());
				}
			}
		}, setDate, MILLI_OF_DAY);
		return timer;
	}

	/**
	 * 保存されたアラームデータを読み込む
	 */
	public void loadConfig() {
		dataMap = ConfigUtil.loadAlarmFile();
		for (Entry<String, AlarmData> element : dataMap.entrySet()) {
			if (element.getValue().isOn()) {
				setAlarm(element.getValue(), element.getKey());
			}
		}
	}

	/**
	 * セットされたアラームデータを保存する
	 */
	public void saveConfig() {
		ConfigUtil.createAlarmFile(dataMap);
		allTimerPurge();
	}

	/**
	 * 全てのアラームタイマーをキャンセルする
	 */
	public void allTimerPurge() {
		for (Timer timer : timers.values()) {
			timer.cancel();
			timer.purge();
		}
	}

}
