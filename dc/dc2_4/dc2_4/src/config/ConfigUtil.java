package dc2_4.src.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import dc2_4.src.alarm.AlarmData;

public class ConfigUtil {

	private static final String SEPARATOR = ",";
	private static final String LINE_SEPARATOR = "\r\n";
	private static final int ALARM_ELEMENT_COUNT = 5;

	/**
	 * 下記要素のCSVファイルを{@link GlobalConfig#ALARM_SETTING_FILE}に保存する<br>
	 * alarmname, on/off, hour, minute, soundPath
	 */
	public static void createAlarmFile(Map<String, AlarmData> dataMap) {
		File file = new File(GlobalConfig.getAlarmSettingFile());
		if (file == null) {
			return;
		}

		try {
			file.delete();
			file.createNewFile();
		} catch (SecurityException | IOException e) {
			System.out.println(e.getMessage());
		}

		try(FileWriter writer = new FileWriter(file.getAbsolutePath())){
			for (Entry<String, AlarmData> element : dataMap.entrySet()) {
				AlarmData data = element.getValue();
				writer.append(element.getKey());
				writer.append(SEPARATOR);
				writer.append(String.valueOf(data.isOn()));
				writer.append(SEPARATOR);
				writer.append(String.valueOf(data.getHour()));
				writer.append(SEPARATOR);
				writer.append(String.valueOf(data.getMinute()));
				writer.append(SEPARATOR);
				writer.append(data.getSound());
				writer.append(LINE_SEPARATOR);
			}
		} catch (IOException e) {
			System.out.println("Fail to save alarm settings file");
		}
	}

	/**
	 * 下記要素のCSVファイルを{@link GlobalConfig#ALARM_SETTING_FILE}から読み込む<br>
	 * alarmname, on/off, hour, minute, soundPath
	 */
	public static Map<String, AlarmData> loadAlarmFile() {
		Map<String, AlarmData> dataMap = new HashMap<>();

		try (FileInputStream in = new FileInputStream(GlobalConfig.getAlarmSettingFile())) {
			try (InputStreamReader is = new InputStreamReader(in)) {
				try (BufferedReader br = new BufferedReader(is)) {
					String line = "";
					while((line = br.readLine()) != null) {
						String[] data = line.split(SEPARATOR);
						if (data.length == ALARM_ELEMENT_COUNT) {
							String identifier = data[0];
							boolean isON = Boolean.valueOf(data[1]);
							int hour = Integer.parseInt(data[2]);
							int minute = Integer.parseInt(data[3]);
							String fileName = data[4];
							AlarmData alarmData = new AlarmData(hour, minute, fileName, isON);
							dataMap.put(identifier, alarmData);
						} else {
							throw new IOException("File element is not correct. The number of element is 5.");
						}
					}
				}
			}
		}catch (FileNotFoundException e) {
		} catch (Exception e) {
			System.out.println("Fail to load setting files. " + e.getMessage());
			return Collections.emptyMap();
		}
		return dataMap;
	}
}
