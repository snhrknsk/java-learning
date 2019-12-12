package ch13.ex06;

import static org.junit.Assert.*;

import org.junit.Test;


public class DecimalSepratorTest {

	@Test
	public void test() {
		String expected = "1,543,729";
		String actual = DecimalSeprator.separate("1543729",3,',');
		assertEquals(expected, actual);

		expected = "12";
		actual = DecimalSeprator.separate("12",3,',');
		assertEquals(expected, actual);

		try {
			actual = DecimalSeprator.separate("12",0,'#');
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

}
