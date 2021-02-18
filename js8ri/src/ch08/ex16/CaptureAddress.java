package ch08.ex16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaptureAddress {

	private static String reg = "(?<city>[\\p{L} ]+),\\s*(?<state>[A-Z]{2})\\s*,\\s*(?<zipCode>[0-9]{5}(-[0-9]{4})?)";

	public static void showAddress(String path) {
		Pattern pattern = Pattern.compile(reg);
		try {
			for (String line : Files.readAllLines(Paths.get(path))) {
				Matcher matcher = pattern.matcher(line);
				if (!matcher.matches()) {
					continue;
				}
				System.out.print("City is " + matcher.group("city"));
				System.out.print(", State is " + matcher.group("state"));
				System.out.println(", Zip Code is " + matcher.group("zipCode"));
			}
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
