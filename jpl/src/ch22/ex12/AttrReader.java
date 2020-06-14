package ch22.ex12;

import java.io.IOException;
import java.io.Reader;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import ch22.ex12.Attributed.Attr;

public class AttrReader {

	private static final String REGEX = "^([^=]*)=([^=]*)$";//name=value形式

	public static Attributed readAttrs(Reader source) throws IOException {
		Attributed attrs = new AttributedImpl();
		Attr attr = null;
		Pattern pat = Pattern.compile(REGEX, Pattern.MULTILINE);
		try(Scanner in = new Scanner(source)){
			while(in.hasNextLine()) {
				String line = in.findInLine(pat);
				if (line != null) {
					MatchResult match = in.match();
					String name = match.group(1);
					Object value = match.group(2);
					attr = new Attr(name, value);
					attrs.add(attr);
					in.nextLine();
				} else {
					throw new IOException("Input format error");
				}
			}
			IOException ex = in.ioException();
			if (ex != null) {
				throw ex;
			}
		} catch (NoSuchElementException e) {//最後に空の行が無い場合in.nextでNoSuchElementエラーが出るが無視
		}
		return attrs;
	}
}
