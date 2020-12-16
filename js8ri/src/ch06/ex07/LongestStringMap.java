package ch06.ex07;

import java.util.concurrent.ConcurrentHashMap;

public class LongestStringMap {

	public static String findMaxKey(ConcurrentHashMap<String, Long> map) {
		return map.reduceEntries(1, (v1, v2) -> {
			return v1.getValue() > v2.getValue() ? v1 : v2;
		}).getKey();
	}
}
