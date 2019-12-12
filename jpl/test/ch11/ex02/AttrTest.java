package ch11.ex02;

import static org.junit.Assert.*;

import org.junit.Test;

public class AttrTest {

	@Test
	public void test() {
		Attr<Integer> attr = new Attr<>("first");

		String actualName = attr.getName();
		assertEquals("first", actualName);
		Integer expectedValue = 1;

		Integer actualValue = attr.getValue();
		assertEquals(null, actualValue);

		attr.setValue(1);
		actualValue = attr.getValue();
		assertEquals(expectedValue, actualValue);

		Integer actualOldValue = attr.setValue(2);
		assertEquals(expectedValue, actualOldValue);

		expectedValue = 2;
		actualValue = attr.getValue();
		assertEquals(expectedValue, actualValue);

	}

}
