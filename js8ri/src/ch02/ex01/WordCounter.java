package ch02.ex01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordCounter {

	private static String SEPARATOR = "[\\P{L}]+";
	private static int SEGMENT_SIZE = 5;
	private static int COUNT = 12;

	public static long countWord(String src) {
		List<String> words = Arrays.asList(src.split(SEPARATOR));
		List<Counter> counter = new ArrayList<>();
		List<Thread> threads = new ArrayList<>();

		for (int i = 0; i < (int) Math.ceil((double)words.size()/SEGMENT_SIZE); i++) {
			int start = i * SEGMENT_SIZE;
			Counter wordCounter = new Counter();
			counter.add(wordCounter);
			//セグメントに区切る
			List<String> subList = words.subList(start, (start + SEGMENT_SIZE) >= words.size() ? words.size() - 1 : start + SEGMENT_SIZE);
			Thread t = new Thread(()->{
				for (String word : subList) {
					if (word.length() > COUNT) {
						System.out.println("Found Word : " + word);
						wordCounter.counIncrement();
					}
				}
			});
			threads.add(t);
			t.start();
		}
		//処理が終わってから合計
		int total = 0;
		for (int i = 0; i < threads.size(); i++) {
			try {
				threads.get(i).join();
				total += counter.get(i).getCount();
			} catch (InterruptedException e) {
			}
		}
		return total;
	}

	public static class Counter{
		private int count = 0;

		public synchronized void counIncrement() {
			count++;
		}

		public long getCount() {
			return count;
		}
	}

	/*
	 * 単一カウンターを更新のためスレッドを利用したくない理由
	 * >スレッドの生成やスレッドが全部終了した状態の監視のコストが大きいため
	 * */
}
