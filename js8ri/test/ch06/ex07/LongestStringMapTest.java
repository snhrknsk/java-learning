package ch06.ex07;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.Test;

class LongestStringMapTest {

	@Test
	void test() {
		String expected = "longestKey";
		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
		map.put("hello", 1L);
		map.put(expected, Long.MAX_VALUE);
		map.put("shortestKey", Long.MIN_VALUE);
		map.put("world", 5L);
		String actual = LongestStringMap.findMaxKey(map);
		assertEquals(expected, actual);
	}

}
