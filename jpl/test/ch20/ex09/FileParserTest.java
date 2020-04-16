package ch20.ex09;

import org.junit.Test;

public class FileParserTest {

	String filePath = "./test/ch20/ex09/test.txt";

	@Test
	public void test() {
		new FileParser(filePath, ".").dispFileInfo();;
	}

}
