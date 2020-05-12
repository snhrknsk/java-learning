package ch22.ex03;

import static org.junit.Assert.*;

import org.junit.Test;


public class BitSetMapTest {

	@Test
	public void test() {
		String expected = "[ 123Teginst]";
		BitSetMap wChar = new BitSetMap("Testing 1 2 3");
		assertTrue(expected.equals(wChar.toString()));
	}

}
