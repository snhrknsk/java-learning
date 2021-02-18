package ch08.ex10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class SearchWordFromFile {

	private static String path = ".\\src\\ch08\\ex10";

	private static boolean hasKeywords(Path path) {
	    try (Scanner scanner = new Scanner(path, "utf-8")) {
	        while (scanner.hasNext()) {
	            String word = scanner.next();
	            if (word.equals("transient") || word.equals("volatile"))
	                return true;
	        }
	        return false;
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        throw new RuntimeException(ex);  // 後で Stream にかけるのでチェック例外は不可
	    }
	}

	public static void main(String[] args) {
		Path root = Paths.get(path);
		try (Stream<Path> stream = Files.walk(root)) {
			stream.filter(p -> p.toFile().isFile()).filter(p -> p.toFile().getName().endsWith(".java")).filter(p -> hasKeywords(p)).forEach(System.out::println);
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
	}
}
