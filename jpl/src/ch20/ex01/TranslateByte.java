package ch20.ex01;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TranslateByte {

	public void translateByte(byte from, byte to, InputStream in, OutputStream out) throws IOException {
		int b;
		while ((b = in.read()) != -1) {
			out.write(b == from ? to : b);
		}
	}

	public static void main(String[] args) throws IOException {
		byte[] buf = "test inputstream".getBytes();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(buf);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		new TranslateByte().translateByte((byte) 't', (byte) 'T', inputStream, outputStream);
		System.out.println(outputStream.toString());
	}
}
