package ch22.ex11;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

	public static List<String[]> readCSV(Reader r, int cellNum) throws IOException{
		List<String[]> result = new ArrayList<>();
		String[] row = new String[cellNum];
		int cellCount = 0;
		StreamTokenizer tokenizer = new StreamTokenizer(r);
		tokenizer.resetSyntax();
		tokenizer.wordChars(1, 43);
		tokenizer.wordChars(45, 127);
		tokenizer.wordChars(128+32,  255);
		tokenizer.whitespaceChars(',', ',');//,を区切り文字
		while(tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
			if (cellCount == cellNum) {
				throw new IOException("The number of element in row exceeds. Defined cell num : " + cellNum);
			}
			if (tokenizer.ttype == StreamTokenizer.TT_WORD) {
				//改行コードがあれば次の文字配列要素へ
				if (tokenizer.sval.contains("\n") || tokenizer.sval.contains("\r") || tokenizer.sval.contains("\r\n")) {
					if (cellCount != cellNum - 1) {
						throw new IOException("The number of element in row is enough. Defined cell num : " + cellNum);
					}
					String[] s = tokenizer.sval.split("[\r\n]");
					row[cellCount] = s[0];
					result.add(row);
					row = new String[cellNum];
					cellCount = 1;
					row[0] = s[2];
				} else {
					row[cellCount] = tokenizer.sval;
					cellCount++;
				}
			}
		}
		if (cellCount != 0) {
			result.add(row);
		}
		return result;
	}
}
