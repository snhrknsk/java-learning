package ch22.ex15;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void test() {
		String source = "9 1 + 2 - 4 / 3 % 2 + sqrt";//sqrt(9+1-2/4%3)=2
		double expected = 2;
		double result = Calculator.calc(source);
		assertEquals(expected, result, 0);
	}

}
