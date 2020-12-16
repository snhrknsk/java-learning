package ch06.ex04;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class FindLongMaxMinTest {

	@Test
	void findMaxTest() {
		List<Long> values = new ArrayList<>(Arrays.asList(1l, 1000L, Long.MAX_VALUE, Long.MIN_VALUE, -300L));
		long actual = FindLongMaxMin.findMaxValue(values.stream());
		assertEquals(Long.MAX_VALUE, actual);
	}

	@Test
	void findMinTest() {
		List<Long> values = new ArrayList<>(Arrays.asList(1l, 1000L, Long.MAX_VALUE, Long.MIN_VALUE, -300L));
		long actual = FindLongMaxMin.findMinValue(values.stream());
		assertEquals(Long.MIN_VALUE, actual);
	}

}
