package ch22.ex10;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ScannerTokenizerTest {

	/*
	 * Comment out#Comment
	 * #Comment1
	 * Simple#Comment2
	 * Complext#Comment3#Comment4
	 * Empty#
	 * No Commnet line
	 * Double##
	 */

	private List<String> expected;
	@Before
	public void setup() {
		expected = new ArrayList<>();
		expected.add("Comment out");
		expected.add("Simple");
		expected.add("Complex");
		expected.add("Empty");
		expected.add("Double");
		expected.add("No Comment line");
	}

	@Test
	public void test() {
		File file = new File("./test/ch22/ex10/Test.txt");
		Reader source;
		try {
			source = new FileReader(file);
			List<String> result = ScannerTokenizer.readWIthoutComment(source);
			System.out.println(result);
			assertEquals(expected.size(), result.size());
			for (int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i));
				assertEquals(expected.get(i), result.get(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
