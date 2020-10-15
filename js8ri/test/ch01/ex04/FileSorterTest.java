package ch01.ex04;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

class FileSorterTest {

	private static String rootdir = "./TargetDir";
	//root dir is different in Eclipse env
	// ".\\test\\ch01\\ex04\\TargetDir";

	private static String second = rootdir + "/Bar";
	private static String third = rootdir + "/Test";
	private static String fourth = rootdir + "/Bar/bar.txt";
	private static String fifth = rootdir + "/Test/abc.txt";
	private static String sixth = rootdir + "/Test/test.txt";
	private static String seventh = rootdir + "/root.txt";

	@Test
	void compareAll() {
		File[] expected = {new File(rootdir), new File(second), new File(third), new File(fourth), new File(fifth), new File(sixth), new File(seventh)};
		File[] files = {new File(rootdir), new File(fifth),new File(seventh), new File(second), new File(sixth), new File(fourth), new File(third)};

		FileSorter.getSortedFile(files);
		for (int i = 0; i < files.length; i++) {
			System.out.println(files[i].getPath());
			assertEquals(expected[i].getPath(), files[i].getPath());
		}
	}

	@Test
	void compareDirFile() {
		File[] expected = {new File(fifth), new File(sixth)};
		File[] files = {new File(sixth), new File(fifth)};

		FileSorter.getSortedFile(files);
		for (int i = 0; i < files.length; i++) {
			assertEquals(expected[i].getPath(), files[i].getPath());
		}
	}

	@Test
	void compareDirName() {
		File[] expected = {new File(second), new File(third)};
		File[] files = {new File(third), new File(second)};

		FileSorter.getSortedFile(files);
		for (int i = 0; i < files.length; i++) {
			assertEquals(expected[i].getPath(), files[i].getPath());
		}
	}

	@Test
	void compareFileName() {
		File[] expected = {new File(rootdir), new File(seventh)};
		File[] files = {new File(seventh), new File(rootdir)};

		FileSorter.getSortedFile(files);
		for (int i = 0; i < files.length; i++) {
			assertEquals(expected[i].getPath(), files[i].getPath());
		}
	}
}
