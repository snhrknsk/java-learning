package ch20.ex05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.LineNumberReader;


public class SearchWordReader extends FilterReader {

	private LineNumberReader lineNumberReader;

	public SearchWordReader(File file) throws FileNotFoundException{
		super(new FileReader(file));
		lineNumberReader = new LineNumberReader(this);
	}

	public String searchWithLineNo(String target) throws IOException {

		StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = lineNumberReader.readLine()) != null) {
        	if (line.contains(target)) {
        		stringBuilder.append("Line");
        		stringBuilder.append(lineNumberReader.getLineNumber());
        		stringBuilder.append(" : ");
			}
			stringBuilder.append(line);
			stringBuilder.append(System.lineSeparator());
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
	}
}
