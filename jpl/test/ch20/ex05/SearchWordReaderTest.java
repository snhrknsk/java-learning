package ch20.ex05;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class SearchWordReaderTest {

	@Test
	public void test() {
		File file = new File("./test/ch20/ex05/Test.txt");
		String separator = System.lineSeparator();
		String expected = "Test" + separator +
				"Line2 : test" + separator +
				"Line3 : hoge test" + separator +
				"Line4 : testtext" + separator +
				"foo" + separator +
				"fo" + separator;
		try (SearchWordReader searchWordReader = new SearchWordReader(file)){
			String result = searchWordReader.searchWithLineNo("test");
			System.out.println(expected);
			assertEquals(expected, result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
