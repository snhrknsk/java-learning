package ch10.ex02;

import static org.junit.Assert.*;

import org.junit.Test;

import ch10.ex01.ConvertString;

public class ConvertStringTest {

	@Test
	public void test() {
		String expected = "a\\n\\t\\bb\\\"\\'\\\\\\f\\rc";
		ConvertString convertString = new ConvertString();
		String actual = convertString.convert("a\n\t\bb\"\'\\\f\rc");
		assertEquals(expected, actual);
	}

}
