package ch22.ex12;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import org.junit.Before;
import org.junit.Test;

import ch22.ex12.Attributed.Attr;

public class AttrReaderTest {

	private final File file = new File("./test/ch22/ex12/Test.txt");
	private AttributedImpl expected;

	@Before
	public void setUp() {
		expected = new AttributedImpl();
		expected.add(new Attr("name", "foo"));
		expected.add(new Attr("name1", "bar"));
		expected.add(new Attr("name2", "hoge"));
		expected.add(new Attr("name3", "Hello world."));
		expected.add(new Attr("name4", ""));
		expected.add(new Attr("name5", "hoge hoge"));
	}

	@Test
	public void test() {

		Reader source;
		Attributed attrs;
		try {
			source = new FileReader(file);
			attrs = AttrReader.readAttrs(source);
			for (Attr attr : expected) {
				assertEquals(attr.getValue(), attrs.find(attr.getName()).getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
