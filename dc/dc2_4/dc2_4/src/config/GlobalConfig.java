package dc2_4.src.config;

import javax.swing.JFrame;


public class GlobalConfig {

	/**
	 * Jarファイル作成時は"." + FILE_SEPARATOR + "configuration"
	 * Eclipse実行時は"." + FILE_SEPARATOR + "dc1_4" + FILE_SEPARATOR + "configuration"
	 */
	private static String FILE_SEPARATOR = System.getProperty("file.separator");
	private static String CONF_FILE_BASE = "." + FILE_SEPARATOR + "configuration";
	private static String ICON_FILE = FILE_SEPARATOR + "icon" + FILE_SEPARATOR + "icon.png";
	private static String SOUND_FILE = FILE_SEPARATOR + "sound";
	private static String ALARM_SETTING_FILE = FILE_SEPARATOR + "alarmconf" + FILE_SEPARATOR + "savedalarm.csv";

	public static JFrame ownerInstance = null;
	public static JFrame getOwnerFrame() {
		return ownerInstance;
	}
	public static void setOwnerFrame(JFrame owner) {
		ownerInstance = owner;
	}

	public static String getFileSeparator() {
		return FILE_SEPARATOR;
	}
	public static void setConfFileBase(String path) {
		CONF_FILE_BASE = path;
	}
	public static String getConfFileBasePath() {
		return CONF_FILE_BASE;
	}
	public static String getIconFile() {
		return CONF_FILE_BASE + ICON_FILE;
	}
	public static String getSoundDirectoryPath() {
		return CONF_FILE_BASE + SOUND_FILE;
	}
	public static String getAlarmSettingFile() {
		return CONF_FILE_BASE + ALARM_SETTING_FILE;
	}

}
