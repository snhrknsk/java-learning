package ch08.ex07;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ReversedFuncTest {

	@Test
	void test() {
		String[] src = {"abc", null, "bcd", "123"};
		String[] expected = {"bcd", "abc", "123", null};
		Arrays.sort(src, ReversedFunc.getNullsFirstComparator());
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], src[i]);
		}

	}

}
