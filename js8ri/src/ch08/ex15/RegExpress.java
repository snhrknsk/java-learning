package ch08.ex15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class RegExpress {

	public static void grepReg(String path, String reg) {
		Pattern pattern = Pattern.compile(reg);
		Path p = Paths.get(path);
		try (Stream<String> stream = Files.lines(p)) {
		    stream.filter(pattern.asPredicate()).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
