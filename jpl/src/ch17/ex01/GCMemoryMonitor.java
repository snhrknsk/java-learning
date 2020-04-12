package ch17.ex01;

import java.util.HashMap;
import java.util.Map;

public class GCMemoryMonitor {

	private static Map<String, Integer> map;

	public static void main(String[] args) throws InterruptedException {

		Runtime rt = Runtime.getRuntime();

		System.out.println("Memory Resource");
		System.out.println("Total " +  rt.totalMemory());
		System.out.println("MAX   " + rt.maxMemory());

		System.out.println("Start-up:");
		System.out.println("Free " + rt.freeMemory());
		//オブジェクト作成
		map = new HashMap<>();
		for (int i = 0; i < 10000; i++) {
			map.put(String.valueOf(i), i);
		}
		System.out.println("After create Object:");
		System.out.println("Free " + rt.freeMemory());
		//オブジェクトリリース
		map.clear();
		map = null;

		//GC
		long isFree = rt.freeMemory();
		long wasFree;
		do {
			wasFree = isFree;
			rt.runFinalization();
			rt.gc();
			isFree = rt.freeMemory();
		} while (isFree > wasFree);
		System.out.println("After GC:");
		System.out.println("Free " + rt.freeMemory());
	}

}
