package ch13.ex01;

import static org.junit.Assert.*;

import org.junit.Test;

public class CharFindTest {

	@Test
	public void test() {
		int expected = 5;
		int actual = CharFind.findCount("ababababa", 'a');
		assertEquals(expected, actual);

		expected = 0;
		actual = CharFind.findCount("ABABABA", 'a');
		assertEquals(expected, actual);

	}

}
