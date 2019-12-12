package ch09.ex02;

import static org.junit.Assert.*;

import org.junit.Test;

public class BitCalcTest {

	@Test
	public void test() {
		int expected = 10;
		BitCalc bit = new BitCalc();
		int actual = bit.bitCount(1023);
		assertEquals(expected, actual);

		expected = 0;
		actual = bit.bitCount(0);
		assertEquals(expected, actual);
	}

}
