package ch06.ex01;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class StringManagerTest {

//	private static String path = "./alice.txt";
	private static String path = "./test/ch06/ex01/alice.txt"; //Eclipse上でのテスト実行パス

	@Test
	void test() throws IOException {
		String expected = "conversations";

		String words = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
		List<String> wordList = Arrays.asList(words.split("[\\P{L}]+"));

		StringManager stringManager = new StringManager(wordList.stream());
		assertEquals(expected, stringManager.findLongestString());

	}

}
