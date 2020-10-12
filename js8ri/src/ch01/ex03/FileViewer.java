package ch01.ex03;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileViewer {

	private static final String directoryPath = "C:\\GitFolder\\java-learning\\js8ri\\src\\ch01\\ex03";

	public static List<String> getAllFile(File file, String extension) {
		List<String> result = new ArrayList<>();
		result.addAll(Arrays.asList(file.list((f, s)->s.endsWith(extension))));
		return result;
	}

	public static void main(String[] args) {

		List<String> result = getAllFile(new File(directoryPath), ".java");
		System.out.println(result);
	}
}
