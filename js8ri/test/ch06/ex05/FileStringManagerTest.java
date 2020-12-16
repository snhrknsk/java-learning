package ch06.ex05;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class FileStringManagerTest {

//	private static String path = "./alice.txt";
//	private static String path = "./test.txt";
	private static String path = "./test/ch06/ex05/alice.txt"; //Eclipse上でのテスト実行パス
	private static String path2 = "./test/ch06/ex05/test.txt"; //Eclipse上でのテスト実行パス

	@Test
	void test() throws IOException {
		List<File> fileList = new ArrayList<>();
		fileList.add(new File(path));
		fileList.add(new File(path2));
		Map<String, Set<File>> stringMap = FileStringManager.loadFiles(fileList);
		Set<File> fileInclude_both = stringMap.get("beginning");
		assertEquals(2, fileInclude_both.size());

		Set<File> fileIncludeIn_alice = stringMap.get("tired");
		assertEquals(1, fileIncludeIn_alice.size());

		Set<File> fileIncludeIn_test = stringMap.get("compared");
		assertEquals(1, fileIncludeIn_test.size());
	}

}
