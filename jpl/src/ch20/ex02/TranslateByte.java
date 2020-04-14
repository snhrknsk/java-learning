package ch20.ex02;

import java.io.ByteArrayInputStream;
import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class TranslateByte extends FilterReader {

	private final byte from;
	private final byte to;

	public TranslateByte(Reader in, byte from, byte to) {
		super(in);
		this.from = from;
		this.to = to;
	}

	@Override
	public int read() throws IOException {
		int c = super.read();
		return (c == -1 ? c : translate(c));
	}

	private int translate(int c) {
		if (c == from) {
			return to;
		}
		return c;
	}

	public static void main(String[] args) {
		byte[] buf = "test inputstream".getBytes();
		FilterReader f = new TranslateByte(new InputStreamReader(new ByteArrayInputStream(buf)), (byte) 't', (byte)'T');
		int c;
		try {
			while ((c = f.read()) != -1) {
				System.out.print((char) c);
			}
		} catch (IOException e) {
		}
	}

}
