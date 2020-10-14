package ch02.ex10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class DoubleStreamAveTest {

	@Test
	void test() {
		double expected = 2.5;
		double result = DoubleStreamAve.average(Stream.of(0.0, 1.5, 6.0, 2.5));
		assertEquals(expected, result);
	}

	@Test
	void testEmpty() {
		double expected = Double.NaN;
		double result = DoubleStreamAve.average(Stream.of());
		assertEquals(expected, result);
	}

}
