package ch09.ex07;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class WebPageUtilTest {

	private static String url = "https://google.co.jp/";

//	private static String path = "./Result.txt";
	private static String dest =  "./test/ch09/ex07/Result.txt"; //Eclipse上でのテスト実行パス

	@Test
	void test() throws IOException {
		WebPageUtil.saveWebPageContent(url, dest);
	}

}
