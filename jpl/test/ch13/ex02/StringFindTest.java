package ch13.ex02;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringFindTest {

	@Test
	public void test() {
		int expected = 5;
		int actual = StringFind.findCount("abcabcababbabb", "ab");
		assertEquals(expected, actual);

		expected = 0;
		actual = StringFind.findCount("ABCABCABABBABB", "ab");
		assertEquals(expected, actual);

		expected = 0;
		actual = StringFind.findCount("ABCABCABABBABB", "");
		assertEquals(expected, actual);

	}

}
