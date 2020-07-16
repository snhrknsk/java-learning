package ch20.ex03;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends FilterInputStream {

	private final byte key;

	protected DecryptInputStream(InputStream in, byte key) {
		super(in);
		this.key = key;
	}

	@Override
	public int read(byte b[], int off, int len) throws IOException {
        int nread = super.read(b, off, len);
		int last = off + nread;
		for (int i = off; i < last; i++) {
			b[i] = (byte) decryptXOR(b[i]);
		}
		return nread;
	}

	@Override
	public int read() throws IOException {
		int c = super.read();
        return c == -1 ? c : decryptXOR(c);
    }

	private int decryptXOR(int c) {
		return c^key;
	}
}
