package ch02.ex12;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import ch02.ex12.WordCountPallarerl;

class WordCountPallarerlTest {

	@Test
	void test() {
		int[] expected = {0, 1, 0, 1 , 2};
		List<String> list = Arrays.asList("this", "a", "test", "hogehoge", "foo");
		AtomicInteger[] result = WordCountPallarerl.countShortWord(list.stream());
		for (int i = 0; i < result.length; i++) {
			assertEquals(expected[i], result[i].get());
		}
	}

	@Test
	void testEmpty() {
		int[] expected = {0, 0, 0, 0 , 0};
		List<String> list = Arrays.asList();
		AtomicInteger[] result = WordCountPallarerl.countShortWord(list.stream());
		for (int i = 0; i < result.length; i++) {
			assertEquals(expected[i], result[i].get());
		}
	}

}
