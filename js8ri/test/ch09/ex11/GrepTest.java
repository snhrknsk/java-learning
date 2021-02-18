package ch09.ex11;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class GrepTest {

//	private static String path = "./dir";
	private static String path =  "./test/ch09/ex11/dir"; //Eclipse上でのテスト実行パス

	@Test
	void test() throws IOException {
		Grep.grepCreditCardNo(path);
	}

}
