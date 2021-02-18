package ch09.ex02;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TryResource {

	public static void main(String[] args) {

		Scanner in;
		try {
			in = new Scanner(Paths.get(""));
		} catch (IOException ex) {
			return;
		}

		PrintWriter out = null;
		try {
			out = new PrintWriter("");
			try {
				while (in.hasNext()) {
				    out.println(in.next().toLowerCase());
				}
			} catch (IllegalStateException | NoSuchElementException ex) {
				try {
					out.close();
				}catch (Exception e) {
					ex.addSuppressed(e);
				}
				try {
					in.close();
				}catch (Exception e) {
					ex.addSuppressed(e);
				}
			}
		} catch (FileNotFoundException ex) {
			try {
				in.close();
			} catch (IllegalStateException e) {
				ex.addSuppressed(e);
			}
			return;
		}
	}
}
