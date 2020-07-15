package dc2_4.src.alarm;

import dc2_4.src.config.GlobalConfig;

public class AlarmData {
	private int hour;
	private int minute;
	private String soundFile;
	private String soundFilePath;
	private boolean on;

	public AlarmData(int hour, int minute, String file, boolean on){
		this.hour = hour;
		this.minute = minute;
		this.soundFile = file;
		this.on = on;
		this.soundFilePath = GlobalConfig.getSoundDirectoryPath() + GlobalConfig.getFileSeparator() + file;
	}

	public boolean isOn() {
		return on;
	}
	public void setOn(boolean on) {
		this.on = on;
	}
	public int getHour() {
		return hour;
	}
	public int getMinute() {
		return minute;
	}
	public String getSoundFilePath() {
		return soundFilePath;
	}
	public String getSound() {
		return soundFile;
	}
}
