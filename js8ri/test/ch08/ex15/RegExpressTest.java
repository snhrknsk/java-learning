package ch08.ex15;

import org.junit.jupiter.api.Test;

class RegExpressTest {

//	private static String path = "./Alice.txt";
	private static String path =  "./test/ch08/ex15/Alice.txt"; //Eclipse上でのテスト実行パス

	@Test
	void test() {
		String reg = "rabbit-hole";
		RegExpress.grepReg(path, reg);
	}

}
