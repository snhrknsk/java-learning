package ch21.ex04;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class ShortStringTest {

	private List<String> strings = new ArrayList<>();

	@Before
	public void before() {
		strings.add("12345678");
		strings.add("1234");
		strings.add("1");
		strings.add("12345");
	}

	@Test(expected = NoSuchElementException.class)
	public void testSimpleNet() {

		int max = 4;
		ShortString shortString = new ShortString(strings.listIterator(), max);
		assertEquals(1, shortString.nextIndex());
		assertEquals(-1, shortString.previousIndex());
		assertEquals(true, shortString.hasNext());
		assertEquals("1234", shortString.next());

		assertEquals(1, shortString.previousIndex());
		assertEquals(2, shortString.nextIndex());
		assertEquals(true, shortString.hasNext());
		assertEquals("1", shortString.next());

		assertEquals(2, shortString.previousIndex());
		assertEquals(4, shortString.nextIndex());
		assertEquals(false, shortString.hasNext());
		shortString.next();
	}

	@Test(expected = NoSuchElementException.class)
	public void testComplex() {
		int max = 4;
		ShortString shortString = new ShortString(strings.listIterator(), max);
		while(shortString.hasNext()) {
			shortString.next();
		}
		assertEquals(4, shortString.nextIndex());
		assertEquals(2, shortString.previousIndex());
		assertEquals(true, shortString.hasPrevious());
		assertEquals("1", shortString.previous());

		assertEquals(2, shortString.nextIndex());
		assertEquals(1, shortString.previousIndex());
		assertEquals(true, shortString.hasPrevious());
		assertEquals("1234", shortString.previous());

		assertEquals(1, shortString.nextIndex());
		assertEquals(-1, shortString.previousIndex());
		assertEquals(true, shortString.hasNext());
		assertEquals(false, shortString.hasPrevious());
		assertEquals("1234", shortString.next());
		assertEquals("1", shortString.next());
		assertEquals(4, shortString.nextIndex());
		shortString.next();
	}

	@Test(expected = NoSuchElementException.class)
	public void testSimplePrev() {
		int max = 4;
		ShortString shortString = new ShortString(strings.listIterator(), max);
		while(shortString.hasNext()) {
			shortString.next();
		}
		assertEquals(4, shortString.nextIndex());
		assertEquals(2, shortString.previousIndex());
		assertEquals(true, shortString.hasPrevious());
		assertEquals("1", shortString.previous());

		assertEquals(2, shortString.nextIndex());
		assertEquals(1, shortString.previousIndex());
		assertEquals(true, shortString.hasPrevious());
		assertEquals("1234", shortString.previous());

		assertEquals(1, shortString.nextIndex());
		assertEquals(-1, shortString.previousIndex());
		assertEquals(false, shortString.hasPrevious());
		shortString.previous();
	}

	@Test
	public void testSet() {
		int max = 4;
		strings.add("12");
		ShortString shortString = new ShortString(strings.listIterator(), max);
		try {
			shortString.set("test");
			fail();
		} catch (IllegalStateException e) {
		}
		shortString.next();
		shortString.set("1111");
		assertEquals(1, shortString.previousIndex());
		assertEquals(2, shortString.nextIndex());
		assertEquals(true, shortString.hasPrevious());
		assertEquals("1111", shortString.previous());
		assertEquals("1111", shortString.next());
		while(shortString.hasNext()) {
			shortString.next();
		}
		shortString.previous();
		shortString.previous();
		assertEquals(2, shortString.nextIndex());
		shortString.set("123456");
		//12345678, 1111, 123456, 12345, 12
		assertEquals(1, shortString.previousIndex());
		assertEquals(4, shortString.nextIndex());
		assertEquals(true, shortString.hasNext());
		assertEquals("12", shortString.next());
	}

	@Test
	public void testAdd() {
		int max = 4;
		ShortString shortString = new ShortString(strings.listIterator(), max);
		shortString.add("12");
		assertEquals(0, shortString.previousIndex());
		assertEquals("12", shortString.previous());

	}

	@Test
	public void testRemove() {
		int max = 4;
		ShortString shortString = new ShortString(strings.listIterator(), max);
		shortString.next();
		shortString.remove();
		assertEquals(-1, shortString.previousIndex());
		assertEquals(1, shortString.nextIndex());
		assertEquals(false, shortString.hasPrevious());
		assertEquals("1", shortString.next());
	}

}
