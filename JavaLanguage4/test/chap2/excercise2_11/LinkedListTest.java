package chap2.excercise2_11;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	LinkedList list;

	@Test
	public void testAdd() {
		list = new LinkedList("test1");
		list.add("test2");
		list.add("test3");
		assertEquals("test1", list.getElement());
		assertEquals("test2", list.getNext().getElement());
		assertEquals("test3", list.getNext().getNext().getElement());
	}

	@Test
	public void testToString() {
		String expected = "[1,2,3]";
		list = new LinkedList("1");
		list.add("2");
		list.add("3");
		assertEquals(expected, list.toString());
	}

}
