package ch02.ex09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class ListCombineTest {

	@Test
	void testCombine() {
		List<String> expected = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i");
		ArrayList<String> list = new ArrayList<String>(){
			{
			add("a"); add("b"); add("c");
			}
		};
		ArrayList<String> list2 = new ArrayList<String>(){
			{
			add("d"); add("e"); add("f");
			}
		};
		ArrayList<String> list3 = new ArrayList<String>(){
			{
			add("g"); add("h"); add("i");
			}
		};
		List<String> result = ListCombine.getCombinedList(Stream.of(list, list2, list3));

		assertEquals(expected.size(), result.size());
		for (int i = 0; i < result.size(); i++) {
			assertEquals(expected.get(i), result.get(i));
		}
	}

	@Test
	void testSingle() {
		List<String> expected = Arrays.asList("a", "b", "c");
		ArrayList<String> list = new ArrayList<String>(){
			{
			add("a"); add("b"); add("c");
			}
		};
		List<String> result = ListCombine.getCombinedList(Stream.of(list));

		assertEquals(expected.size(), result.size());
		for (int i = 0; i < result.size(); i++) {
			assertEquals(expected.get(i), result.get(i));
		}
	}

	@Test
	void testEmpty() {
		List<String> expected = Arrays.asList();
		ArrayList<String> list = new ArrayList<String>();
		List<String> result = ListCombine.getCombinedList(Stream.of(list));

		assertEquals(expected.size(), result.size());
		for (int i = 0; i < result.size(); i++) {
			assertEquals(expected.get(i), result.get(i));
		}
	}

}
