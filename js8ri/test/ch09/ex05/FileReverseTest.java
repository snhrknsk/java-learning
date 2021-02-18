package ch09.ex05;

import org.junit.jupiter.api.Test;

class FileReverseTest {

//	private static String path = "./Test.txt";
	private static String src =  "./test/ch09/ex05/Test.txt"; //Eclipse上でのテスト実行パス

//	private static String path = "./Result.txt";
	private static String dest =  "./test/ch09/ex05/Result.txt"; //Eclipse上でのテスト実行パス

	@Test
	void test() {
		FileReverse.reverseFileContents(src, dest);
	}

}
