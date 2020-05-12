package ch22.ex02;

import static org.junit.Assert.*;

import org.junit.Test;

public class WhichCharsTest {

	@Test
	public void test() {

		String expected = "[ 123Teginst]";
		WhichChars wChar = new WhichChars("Testing 1 2 3");
		assertTrue(expected.equals(wChar.toString()));
	}

}
