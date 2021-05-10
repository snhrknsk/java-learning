package ch08.ex05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;


class WordCounterTest {

//	private static String path = "./LongText.txt";
	private static String path =  "./test/ch08/ex05/LongText.txt"; //Eclipse上でのテスト実行パス

	@Test
	void test() {
		try {
			String src = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
			ArrayList<String> words = new ArrayList<String>(Arrays.asList(src.split("[\\P{L}]+")));

			long start = System.currentTimeMillis();
			WordCounter.wordCountCh02(words);
			System.out.println("Not Stream: " + (System.currentTimeMillis() - start));

			start = System.currentTimeMillis();
			WordCounter.wordCount(words);
			System.out.println("Stream: " + (System.currentTimeMillis() - start));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
