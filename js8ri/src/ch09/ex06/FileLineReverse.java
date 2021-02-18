package ch09.ex06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileLineReverse {

	public static void reverseFileLineContent(String src, String dest) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(src));
			Collections.reverse(lines);
			Files.write(Paths.get(dest), lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
