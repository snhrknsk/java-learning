package ch23.ex03;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.Charset;

public class CommandExe {

	public static void execCommand(String command, String endStr){
		System.out.println(command);
		try {
			String[] comConv = command.split(" ");
			Process child = Runtime.getRuntime().exec(comConv);
			InputStreamReader in = new InputStreamReader(child.getInputStream(), Charset.forName("Shift-JIS"));
			try (LineNumberReader reader = new LineNumberReader(in)) {
				String line;
				//一行ごとに読み込んで書き出す
				while ((line = reader.readLine()) != null) {
					if (line.contains(endStr)) {
						child.destroy();
					}
					System.out.printf("%3d %s\n", reader.getLineNumber(), line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		execCommand("ping 10.0.0.1", "タイムアウト");
		execCommand("ipconfig /flushdns", "test");
	}
}
