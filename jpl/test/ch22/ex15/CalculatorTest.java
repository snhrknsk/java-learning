package ch22.ex15;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void test() {
		String source = "9 1 + 2 - 4 / 3 %";//=2
		double result = Calculator.calc(source);
		System.out.println(result);
	}

}
