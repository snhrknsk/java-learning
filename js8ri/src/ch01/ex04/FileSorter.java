package ch01.ex04;

import java.io.File;
import java.util.Arrays;

public class FileSorter {

	public static File[] getSortedFile(File[] files){
		Arrays.sort(files, (file1, file2) -> {
			if (file1.isDirectory() && file2.isFile()) {
				return -1;
			} else if (file1.isFile() && file2.isDirectory()) {
				return 1;
			}
			return file1.getPath().compareTo(file2.getPath());
		});
		return files;
	}

}
