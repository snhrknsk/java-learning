package ch08.ex16;

import org.junit.jupiter.api.Test;

class CaptureAddressTest {

//	private static String path = "./Address.txt";
	private static String path =  "./test/ch08/ex16/Address.txt"; //Eclipse上でのテスト実行パス

	@Test
	void test() {
		CaptureAddress.showAddress(path);
	}

}
