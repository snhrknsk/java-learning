package ch20.ex08;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class EntryReaderTest {

	String srcText;
	String srcPath = "./test/ch20/ex08/SrcFile.txt";
	String createdFilePath = "./test/ch20/ex08/EntryFile.txt";

	@Test
	public void test() {
		File file = new File(srcPath);
		EntryReader entryReader = new EntryReader(file);
		try {
			entryReader.parseEntry();
			File entryFile = new File(createdFilePath);
			assertTrue(entryFile.exists());
			entryReader.dispRndomEntry();
		} catch (IOException e) {
			fail();
		}

	}

}
