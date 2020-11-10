package ch03.ex07;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ComparatorGeneratorTest {


	@Test
	void testNaturalOrder() {
		String[] expected = {"Ca", "a", "cd"};
		String[] src = {"cd", "Ca", "a"};
		Arrays.sort(src, ComparatorGenerator.naturalOrder());
		for (int i = 0; i < src.length; i++) {
			assertEquals(expected[i], src[i]);
		}
	}

	@Test
	void testReverseOrder() {
		String[] expected = {"cd", "a", "Ca"};
		String[] src = {"cd", "Ca", "a"};
		Arrays.sort(src, ComparatorGenerator.reverseOrder());
		for (int i = 0; i < src.length; i++) {
			assertEquals(expected[i], src[i]);
		}
	}

	@Test
	void testNaturalIGnoreCaseOrder() {
		String[] expected = {"a", "Ca", "cd"};
		String[] src = {"cd", "Ca", "a"};
		Arrays.sort(src, ComparatorGenerator.ignoreCase(ComparatorGenerator.naturalOrder()));
		for (int i = 0; i < src.length; i++) {
			assertEquals(expected[i], src[i]);
		}
	}

	@Test
	void testReverseIGnoreCaseOrder() {
		String[] expected = {"cd", "Ca", "a"};
		String[] src = {"cd", "Ca", "a"};
		Arrays.sort(src, ComparatorGenerator.ignoreCase(ComparatorGenerator.reverseOrder()));
		for (int i = 0; i < src.length; i++) {
			assertEquals(expected[i], src[i]);
		}
	}

	@Test
	void testNaturalIgnoreSpaceSpace() {
		String[] expected = {"aa", "a b", "cd"};
		String[] src = {"a b", "aa", "cd"};
		Arrays.sort(src, ComparatorGenerator.ignoreSpace(ComparatorGenerator.naturalOrder()));
		for (int i = 0; i < src.length; i++) {
			assertEquals(expected[i], src[i]);
		}
	}

	@Test
	void testReverseIgnoreSpaceSpace() {
		String[] expected = {"cd", "a b", "aa"};
		String[] src = {"a b", "aa", "cd"};
		Arrays.sort(src, ComparatorGenerator.ignoreSpace(ComparatorGenerator.reverseOrder()));
		for (int i = 0; i < src.length; i++) {
			assertEquals(expected[i], src[i]);
		}
	}
}
