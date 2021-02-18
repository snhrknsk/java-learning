package ch09.ex11;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

public class Grep {

	public static void grepCreditCardNo(String path) throws IOException {

		ProcessBuilder builder = new ProcessBuilder("grep", "-r", "[0-9]{14,16}", path);
		File home = new File(System.getProperty("user.dir"));
		builder.directory(home);
		File outputFile = new File(path + "/../res.txt");
		builder.redirectOutput(Redirect.to(outputFile));
		Process process = builder.start();
		try {
			process.waitFor();
		} catch (InterruptedException ex) {
		}

	}
}
