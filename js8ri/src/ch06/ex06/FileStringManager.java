package ch06.ex06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class FileStringManager {

	public static ConcurrentHashMap<String, Set<File>> loadFiles(List<File> fileList) throws IOException {
		ConcurrentHashMap<String, Set<File>> stringMap = new ConcurrentHashMap<>();
		Thread[] threads = new Thread[fileList.size()];
		//読み込み実行
		for (int i = 0; i < threads.length; i++) {
			File f = fileList.get(i);
			threads[i] = new Thread(() -> {
				List<String> wordList;
				try {
					wordList = readFileAsString(f);
					for (String word : wordList) {
						stringMap.computeIfAbsent(word, k -> new HashSet<>());
						stringMap.get(word).add(f);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			threads[i].start();
		}
		//処理待ち
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return stringMap;
	}

	private static List<String> readFileAsString(File f) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			StringBuffer sb = new StringBuffer();
			int c;
			while ((c = br.read()) != -1) {
				sb.append((char) c);
			}
			return Arrays.asList(sb.toString().split("[\\P{L}]+"));
		} finally {
			br.close();
		}
	}
}
