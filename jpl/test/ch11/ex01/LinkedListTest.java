package ch11.ex01;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void test() {
		Integer expected = 1;
		LinkedList<Integer> linkedList = new LinkedList<>(1);
		assertEquals(expected, linkedList.getElement());

		linkedList.add(2);
		linkedList.add(3);
		expected = 2;
		assertEquals(expected, linkedList.getNext().getElement());
		expected = 3;
		assertEquals(expected, linkedList.getNext().getNext().getElement());
		expected = null;
		assertEquals(expected, linkedList.getNext().getNext().getNext());
	}

}
