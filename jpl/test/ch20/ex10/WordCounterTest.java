package ch20.ex10;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class WordCounterTest {

	String filePath = "./test/ch20/ex10/Word.txt";

	@Test
	public void test() {
		try {
			WordCounter wordCounter = new WordCounter(new File(filePath));
			wordCounter.dispWordCount();
			int expected = 3;
			int actual = wordCounter.getCount("was");
			assertEquals(expected, actual);
			expected = 0;
			actual = wordCounter.getCount("hoge");
			assertEquals(expected, actual);
		} catch (IOException e) {
			fail();
		}
	}

}
