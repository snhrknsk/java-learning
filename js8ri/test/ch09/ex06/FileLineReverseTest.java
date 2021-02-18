package ch09.ex06;

import org.junit.jupiter.api.Test;

class FileLineReverseTest {

//	private static String path = "./Test.txt";
	private static String src =  "./test/ch09/ex06/Test.txt"; //Eclipse上でのテスト実行パス

//	private static String path = "./Result.txt";
	private static String dest =  "./test/ch09/ex06/Result.txt"; //Eclipse上でのテスト実行パス

	@Test
	void test() {
		FileLineReverse.reverseFileLineContent(src, dest);
	}

}
