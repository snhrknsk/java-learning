package ch01.ex03;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FileViewerTest {

	private static String testdir = "./TargetDir";
	//root dir is different in Eclipse env
	// ".\\test\\ch01\\ex03\\TargetDir";

	@Test
	public void test() {
		String path = new File(".").getAbsoluteFile().getParent();
		System.out.println(path);
		List<String> expected = new ArrayList<>(Arrays.asList("java.java.java"));
		File file = new File(testdir);
		List<String> result = FileViewer.getAllFile(file, ".java");
		assertEquals(expected.size(), result.size());
		for (int i = 0; i < result.size(); i++) {
			assertEquals(expected.get(i), result.get(i));
		}
	}

	@Test
	public void test2() {
		List<String> expected = new ArrayList<>(Arrays.asList("file.txt", "test.txt"));
		File file = new File(testdir);
		List<String> result = FileViewer.getAllFile(file, ".txt");
		assertEquals(expected.size(), result.size());
		for (int i = 0; i < result.size(); i++) {
			assertEquals(expected.get(i), result.get(i));
		}
	}

	@Test
	public void testEmpty() {
		List<String> expected = new ArrayList<>();
		File file = new File(testdir);
		List<String> result = FileViewer.getAllFile(file, ".jpeg");
		assertEquals(expected.size(), result.size());
	}

}
