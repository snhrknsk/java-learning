package ch12.ex01;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void test() {
		LinkedList list = new LinkedList(new Integer(1));

		try {
			Integer expected = 1;
			LinkedList findList = list.find(1);
			assertEquals(expected, findList.getElement());
		} catch (ObjectNotFoundException e) {
		}

		try {
			list.find(2);
			fail();
		} catch (ObjectNotFoundException e) {
			Integer expected = 2;
			assertEquals(expected, e.getSearchObject());
		}
	}

}
