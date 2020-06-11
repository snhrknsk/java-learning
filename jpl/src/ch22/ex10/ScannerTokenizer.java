package ch22.ex10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ScannerTokenizer {

	private static final String REGEX ="#.*[\r\n]" ;

	public static List<String> readWIthoutComment(Readable source){
		List<String> result = new ArrayList<>();
		try(Scanner in = new Scanner(source)){
			in.useDelimiter(Pattern.compile(REGEX));
			while(in.hasNext()) {
				String line = in.next().replaceAll("^[\n\r]", "");
				if (!line.equals("")) {
//					System.out.println("in  :" + line);
					result.add(line);
				}
			}
		}
		return result;
	}
}
