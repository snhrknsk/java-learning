package ch10.ex05;

import static org.junit.Assert.*;

import org.junit.Test;

public class CharGeneratorTest {

	@Test
	public void test() {
		String expected = "abcdefg";
		String actual = CharGenerator.getCharsSequence('a', 'g');
		assertEquals(expected, actual);

		expected = "ABCDEFG";
		actual = CharGenerator.getCharsSequence('A', 'G');
		assertEquals(expected, actual);

		expected = "ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz";
		actual = CharGenerator.getCharsSequence('A', 'z');
		assertEquals(expected, actual);

		expected = "ぁあぃいぅうぇえぉお";
		actual = CharGenerator.getCharsSequence('ぁ', 'お');
		assertEquals(expected, actual);
	}

}
