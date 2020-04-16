package ch20.ex11;

import java.io.File;
import java.io.FilenameFilter;

public class FileSearch {

	private final File dir;

	public FileSearch(File dir) {
		if (!dir.isDirectory()) {
			throw new IllegalArgumentException("dir must be a directory");
		}
		this.dir = dir;
	}

	public void showFilteredList(String suffix) {

		FilenameFilter filenameFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(suffix);
			}
		};
		File[] list = dir.listFiles(filenameFilter);
		System.out.println("Dir    : " + dir.getName());
		System.out.println("Suffix : " + suffix);
		for (File file : list) {
			System.out.println(" " + file.getName());
		}
	}
}
