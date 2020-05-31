package ch22.ex09;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;


/**
 * CSVを読み込む正規表現<br>
 * 1. ^(.*),(.*)...(165ms)<br>
 * 2. ^([^,]*),([^,]*)...(17ms)<br>
 * 3. ([^,]+),([^,]+)...(13ms)<br>
 * 4. ([^,]+?),([^,]+?)...(9ms)<br>
 * なぜ速度に差が出るか分からない
 */
public class CSVReaderBench {

	private static final int CELLS = 10;
	private static final String[] TEST_REGEX = new String[]
			{ "(.*)", "([^,]*)", "([^,]*+)", "([^,]*?)" };

	public static String benchMark(Readable r, String regex) throws IOException {

		StringBuilder result = new StringBuilder();

		StringBuilder exp = new StringBuilder(regex);
		for (int i = 1; i < CELLS; i++) {
			exp.append(",").append(regex);
		}
		result.append(regex);
		result.append(" : ");
		long start = System.currentTimeMillis();
		try(Scanner in = new Scanner(r)){
			readCSVTable(in, exp.toString());
		}
		result.append(System.currentTimeMillis() - start);
		result.append("[ms]");
		return result.toString();
	}

	private static List<String[]> readCSVTable(Scanner in, String regex) throws IOException{
		List<String[]> vals = new ArrayList<>();
		Pattern pat = Pattern.compile(regex, Pattern.MULTILINE);
		//読み込み
		while(in.hasNextLine()) {
			String line = in.findInLine(pat);
			if (line != null) {
				String[] cells = new String[CELLS];
				MatchResult match = in.match();
				for (int i = 0; i < match.groupCount(); i++) {
					cells[i] = match.group(i+1);
				}
				vals.add(cells);
				in.nextLine();
			} else {
				throw new IOException("Input format error");
			}
			IOException ex = in.ioException();
			if (ex != null) {
				throw ex;
			}
		}
		return vals;
	}

	public static void main(String[] args) {
		File file = new File("./src/ch22/ex09/Test.csv");
		Reader source;
		for (String regex : TEST_REGEX) {
			try {
				source = new FileReader(file);
				System.out.println( benchMark(source, regex) );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
