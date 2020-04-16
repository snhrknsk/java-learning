package ch20.ex11;

import java.io.File;

import org.junit.Test;

public class FileSearchTest {

	String filePath = "./test/ch20/ex11";

	@Test
	public void test() {
		FileSearch fileSearch = new FileSearch(new File(filePath));
		fileSearch.showFilteredList(".txt");
		fileSearch.showFilteredList(".xml");
		fileSearch.showFilteredList("");
	}

}
