package ch23.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyProcess {

	public static Process useProg(String cmd) throws IOException{
		Process proc = Runtime.getRuntime().exec(cmd);
		plugTogether(System.in, proc.getOutputStream());
		plugTogether(System.out, proc.getInputStream());
		plugTogether(System.err, proc.getErrorStream());
		return proc;
	}

	public static void plugTogether(InputStream src, OutputStream dest) throws IOException {
		new Thread(() -> {
			int b;
			try {
				while((b = src.read()) != -1) {
					dest.write(b);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}

	public static void plugTogether(OutputStream src, InputStream dest) throws IOException {
		new Thread(() -> {
			int b;
			try {
				while((b = dest.read()) != -1) {
					src.write(b);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}

	public static void main(String[] args) {
		try {
			Process pro = useProg("ipconfig");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
