package ch23.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 分からない
 */
public class MyProcess {

	public static Process useProg(String cmd) throws IOException{
		Process proc = Runtime.getRuntime().exec(cmd);
		plugTogether(System.in, proc.getOutputStream());
		plugTogether(System.out, proc.getInputStream());
		plugTogether(System.err, proc.getErrorStream());
		return proc;
	}

	public static void plugTogether(Object src, Object dest) throws IOException {
		if (src instanceof InputStream) {
			int b;
			while((b = ((InputStream) src).read()) != -1) {
				((OutputStream)dest).write(b);
			}
		} else if (src instanceof OutputStream) {
			int b;
			while((b = ((InputStream) dest).read()) != -1) {
				((OutputStream)src).write(b);
			}
		}
	}
}
