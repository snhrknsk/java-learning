package ch13.ex04;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TypeObjectGeneratorTest {

	@Test
	public void test() {
		List<Object> expected = new ArrayList<>();
		expected.add(new Boolean(true));
		expected.add(new Character('a'));
		expected.add(new Short((short)1));
		expected.add(new Integer(2));
		expected.add(new Double(1.1));
		expected.add(new Float(1.1));
		expected.add(new Byte((byte)1));

		TypeObjectGenerator gen = new TypeObjectGenerator("./test/ch13/ex04/test.txt");
		List<Object> list = gen.create();

		for (int i = 0; i < list.size(); i++) {
			assertEquals(expected.get(i).getClass(), list.get(i).getClass());
			assertEquals(expected.get(i), list.get(i));
		}
	}

}
