package ch21.ex02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.WeakHashMap;

public class DataHandler {

	private Map<File, byte[]> dataMap = new WeakHashMap<>();

	byte[] readFile(File file) {
		byte[] data;

		if (dataMap.containsKey(file)) {
			return dataMap.get(file);
		}
		data = readBytesFromFile(file);
		return data;
	}

	private byte[] readBytesFromFile(File file) {
		byte[] data = null;
		try {
			data = Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
 	}
}
