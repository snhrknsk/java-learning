package ch13.ex03;

import static org.junit.Assert.*;

import org.junit.Test;

public class DelimitedStringTest {

	@Test
	public void test() {
		String[] expected = new String[] {"abc","aabc","abbn"};
		String[] actual = DelimitedString.delimitedString("abcaabcabbn", 'a', 'c');
		assertArrayEquals(expected, actual);

		expected = new String[0];
		actual = DelimitedString.delimitedString("", 'a', 'c');
		assertArrayEquals(expected, actual);
	}

}
