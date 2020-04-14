package ch20.ex03;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptOutputStream extends FilterOutputStream {

	private final byte key;

	public EncryptOutputStream(OutputStream out, byte key) {
		super(out);
		this.key = key;
	}

	@Override
	public void write(int b) throws IOException {
		super.write(encryptXOR(b));
	}

	private int encryptXOR(int b) {
		return b^key;

	}

}
