package ch02.ex06;

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

}
