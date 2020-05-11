package ch21.ex01;

import java.io.ByteArrayInputStream;
import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class SortString  extends FilterReader{

	List<String> stringList = new LinkedList<>();

	public SortString(Reader in) {
		super(in);
	}

	public List<String> sortString() throws IOException{

		String line = "";
		while (!(line = readLine()).equals("")) {
			if (stringList.isEmpty()) {
				stringList.add(line);
			} else {
				ListIterator<String> ite = stringList.listIterator();
				while(ite.hasNext()) {
					if (ite.next().compareTo(line) > 0) {
						ite.previous();
						ite.add(line);
						break;
					}
				}
				if (!ite.hasNext()) {
					stringList.add(line);
				}
			}
		}
		return stringList;
	}

	private String readLine() throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        int b;
        while ((b = super.read()) != -1) {
        	stringBuilder.append((char) b);
        	if ((stringBuilder.toString().endsWith("\n") || stringBuilder.toString().endsWith("\r\n") || stringBuilder.toString().endsWith("\r"))) {
        		return stringBuilder.toString().replace("\n", "").replace("\r\n", "").replace("\r", "");
        	}
        }
        return stringBuilder.toString();
	}

	public static void main(String[] args) {
		byte[] buf = "cbc\nbbc\na".getBytes();
		try (SortString f = new SortString(new InputStreamReader(new ByteArrayInputStream(buf)))){
			System.out.println(f.sortString());
		} catch (IOException e) {
		}
	}
}
