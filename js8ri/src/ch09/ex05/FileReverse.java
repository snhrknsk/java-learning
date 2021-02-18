package ch09.ex05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReverse {

	public static void reverseFileContents(String srcPath, String destPath) {

		try {
			byte[] buffer = Files.readAllBytes(Paths.get(srcPath));
			StringBuffer sb = new StringBuffer(new String(buffer));
			sb.reverse();
			Files.write(Paths.get(destPath), sb.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
