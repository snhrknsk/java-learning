package chap1.exercise1_15;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ExtendedLookupTest {

	private ExtendedLookup lookup;
	@Before
	public void setUp(){
		lookup = new LookupImpl();
	}

	@Test
	public void testFind() {
		String value = "value1";
		lookup.add("test", value);
		assertEquals(value, lookup.find("test"));
	}

	@Test
	public void testNotFind() {
		String value = "value1";
		lookup.add("test", value);
		assertEquals(null, lookup.find("test2"));
	}

	@Test
	public void testRemove() {
		lookup.add("test", "value1");
		lookup.remove("test");
		assertEquals(null, lookup.find("test"));
	}

	@Test
	public void testAddDuplicate() {
		String value = "value1";
		lookup.add("test", "temp");
		lookup.add("test", value);
		assertEquals(value, lookup.find("test"));
	}

	@Test
	public void testAddRemove() {
		lookup.add("test", "value1");
		lookup.add("test2", "value2");
		lookup.remove("test");
		lookup.add("test3", "value3");
		lookup.add("test2", "value2_2");
		lookup.add("test4", "value4");
		lookup.add("test5", "value5");
		lookup.remove("test4");
		lookup.remove("test4");
		assertEquals("value2_2", lookup.find("test2"));
		assertEquals(null, lookup.find("test"));
		assertEquals("value3", lookup.find("test3"));
		assertEquals(null, lookup.find("test4"));
	}

}
