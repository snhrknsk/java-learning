package ch22.ex13;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.junit.Test;

public class AttrReaderTest {

	private final File file = new File("./test/ch22/ex13/Test.txt");

	@Test (expected = IOException.class)
	public void test() throws IOException{

		Reader source = new FileReader(file);
		Attributed attrs = AttrReader.readAttrs(source);
	}

}
