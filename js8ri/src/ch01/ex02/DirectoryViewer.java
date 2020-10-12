package ch01.ex02;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryViewer {

	private static final String directoryPath = "./";

	public static List<File> getAllSubDirectoryByRamda(File file){
		List<File> result = new ArrayList<>();
		File[] files = file.listFiles((f)->f.isDirectory());
		for (File f : files) {
			result.add(f);
			result.addAll(getAllSubDirectoryByRamda(f));
		}
		return result;
	}

	public static List<File> getAllSubDirectoryByRefarenceMethod(File file){
		List<File> result = new ArrayList<>();
		File[] files = file.listFiles(File::isDirectory);
		for (File f : files) {
			result.add(f);
			result.addAll(getAllSubDirectoryByRamda(f));
		}
		return result;
	}

	public static void main(String[] args) {

		System.out.println("Ramda");
		for (File file : getAllSubDirectoryByRamda(new File(directoryPath))) {
			System.out.println(file.getName());
		}

		System.out.println("Method");
		for (File file : getAllSubDirectoryByRefarenceMethod(new File(directoryPath))) {
			System.out.println(file.getName());
		}

	}
}
