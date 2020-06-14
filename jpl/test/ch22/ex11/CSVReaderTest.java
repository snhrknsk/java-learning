package ch22.ex11;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class CSVReaderTest {

	private final File file = new File("./test/ch22/ex11/Test.csv");

	@Test
	public void test() {
		List<String[]> expected = new ArrayList<>();
		expected.add(new String[] {"1", "2","3","4","5"});
		expected.add(new String[] {"6", "7","8"," ","10"});

		Reader source;
		List<String[]> result = Collections.emptyList();
		try {
			source = new FileReader(file);
			result = CSVReader.readCSV(source, 5);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(expected.size(), result.size());
		for (int i = 0; i < result.size(); i++) {
			assertEquals(expected.get(i).length, result.get(i).length);
			for (int j = 0; j < result.get(i).length; j++) {
				assertEquals(expected.get(i)[j], result.get(i)[j]);
			}
		}

	}

	@Test(expected = IOException.class)
	public void testException() throws IOException {
		Reader source;
		source = new FileReader(file);
		CSVReader.readCSV(source, 10);
	}

	@Test(expected = IOException.class)
	public void testCellLessException() throws IOException {
		Reader source;
		source = new FileReader(file);
		CSVReader.readCSV(source, 2);
	}

}
