package ch01.ex04;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileSorter {

	public static File[] getSortedFile(File[] files){
		List<File> list = Arrays.asList(files);

//		list.stream().

		return list.toArray(files);
	}

	public static void main(String[] args) {

	}
}
