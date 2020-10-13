package ch02.ex01;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class WordCounterTest {

	private static String path = "./alice.txt";
	// "./test/ch02/ex01/alice.txt" //Eclipse上でのテスト実行パス

	@Test
	void test() {
		long expected = 3;
		try {
			String src = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
			long result = WordCounter.countWord(src);
			assertEquals(expected, result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
