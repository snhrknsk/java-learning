package ch17.ex01;

import java.util.Map;

public class GCMemoryMonitor {

	private static Map<String, Integer> map;

	public static void main(String[] args) throws InterruptedException {
		showMemory("after startup");
		//オブジェクト作成
		for (int i = 0; i < 1000; i++) {
			map.put(String.valueOf(i), i);
		}
		showMemory("after create objects-");
		//オブジェクトリリース
		map.clear();
		map = null;

		//GC
		Runtime rt = Runtime.getRuntime();
		long isFree = rt.freeMemory();
		long wasFree;
		do {
			wasFree = isFree;
			rt.runFinalization();
			rt.gc();
			isFree = rt.freeMemory();
		} while (isFree > wasFree);
		showMemory("after GC");
	}

	public static void showMemory(String message) {
		Runtime rt = Runtime.getRuntime();
		System.out.println("Memory Monitor");
		System.out.println("free: " + rt.freeMemory());
		System.out.println("total: " +  rt.totalMemory());
		System.out.println("max: " + rt.maxMemory());
	}
}
