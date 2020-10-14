package ch02.ex11;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class StreamCopyAsListTest {

	@Test
	void test() {
		Integer[] expected = {1,2,3,4,5};
		//同じ大きさのArrayListを作る
		ArrayList<Integer> list = new ArrayList<>(Collections.nCopies(5, 0));

		Stream<Integer> stream = Arrays.stream(expected);
		StreamCopyAsList.copyStream2ArrayList(list, stream);
		for (int i = 0; i < expected.length; i++) {
			assertTrue(list.contains(expected[i]));
		}
	}

	@Test
	void testEmpty() {
		Integer[] expected = {};
		//同じ大きさのArrayListを作る
		ArrayList<Integer> list = new ArrayList<>(Collections.nCopies(0, 0));

		Stream<Integer> stream = Arrays.stream(expected);
		StreamCopyAsList.copyStream2ArrayList(list, stream);
		assertEquals(0, list.size());
	}

}
