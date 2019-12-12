package ch13.ex05;

import static org.junit.Assert.*;

import org.junit.Test;

public class DecimalSeparaterTest {

	@Test
	public void test() {
		String expected = "1,543,729";
		String actual = DecimalSeparater.separate("1543729");
		assertEquals(expected, actual);

		expected = "12";
		actual = DecimalSeparater.separate("12");
		assertEquals(expected, actual);
	}

}
