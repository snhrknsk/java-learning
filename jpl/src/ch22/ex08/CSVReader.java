package ch22.ex08;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CSVReader {

	public static List<String[]> readCSVTable(Readable source, int cellNum) throws IOException{
		if (cellNum <= 0) {
			throw new IllegalArgumentException();
		}
		List<String[]> vals = new ArrayList<>();
		try(Scanner in = new Scanner(source)){
			StringBuilder exp = new StringBuilder("^");
			//正規表現作成[^,]*←カンマ以外の0文字以上の文字列
			for (int i = 0; i < cellNum; i++) {
				if (i == 0) {
					exp.append("([^,]*)");
				} else {
					exp.append(",([^,]*)");
				}
			}
			exp.append("$");
			Pattern pat = Pattern.compile(exp.toString(), Pattern.MULTILINE);
			//読み込み
			while(in.hasNextLine()) {
				String line = in.findInLine(pat);
				if (line != null) {
					String[] cells = new String[cellNum];
					MatchResult match = in.match();
					for (int i = 0; i < cellNum; i++) {
						cells[i] = match.group(i+1);
					}
					vals.add(cells);
					in.nextLine();
				} else {
					//改行処理
					line = in.nextLine();
					if (!line.equals("")) {
						throw new IOException("Input format error");
					}
				}
			}
			IOException ex = in.ioException();
			if (ex != null) {
				throw ex;
			}
		}
		return vals;
	}
}
