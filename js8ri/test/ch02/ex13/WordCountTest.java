package ch02.ex13;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class WordCountTest {

	@Test
	void test() {
		long[] expected = {0, 1, 0, 1 , 2};
		List<String> list = Arrays.asList("this", "a", "test", "hogehoge", "foo");
		Map<Integer, Long> result = WordCount.countShortWord(list.stream());
		for (int i = 0; i < result.size(); i++) {
			assertEquals(expected[i], (long)result.get(i));
		}
	}

	@Test
	void testEmpty() {
		int[] expected = {0, 0, 0, 0 , 0};
		List<String> list = Arrays.asList();
		Map<Integer, Long> result = WordCount.countShortWord(list.stream());
		for (int i = 0; i < result.size(); i++) {
			assertEquals(expected[i], (long)result.get(i));
		}
	}

}
