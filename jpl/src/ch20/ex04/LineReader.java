package ch20.ex04;

import java.io.ByteArrayInputStream;
import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;


public class LineReader extends FilterReader {

	public LineReader(Reader in) {
		super(in);
	}

	public String readLine() throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        int b;
        while ((b = super.read()) != -1) {
        	stringBuilder.append((char) b);
        	if (stringBuilder.toString().endsWith("\n") || stringBuilder.toString().endsWith("\r\n") || stringBuilder.toString().endsWith("\r")) {
        		return stringBuilder.toString();
        	}
        }
        return stringBuilder.toString();
	}

	public static void main(String[] args) {
		byte[] buf = "test inputstream\ntest".getBytes();
		LineReader f = new LineReader(new InputStreamReader(new ByteArrayInputStream(buf)));
		String line;
		int lineNo = 0;
		try {
			while (!(line = f.readLine()).equals("")) {
				System.out.print(lineNo + " : " + line);
				lineNo++;
			}
		} catch (IOException e) {
		}
	}

}
