package ch22.ex14;

import static org.junit.Assert.*;

import org.junit.Test;

public class FloatReaderTest {

	@Test
	public void test() {
		String source = "1.1 2.5 4 5.5";
		double expected = 13.1;
		double actual = FloatReader.sum(source);
		assertEquals(expected, actual, 0);
	}

	@Test (expected=NumberFormatException.class)
	public void testInvalidFormat() {
		String source = "1.1 2.5 4gf 5.5";
		double actual = FloatReader.sum(source);
	}
}
