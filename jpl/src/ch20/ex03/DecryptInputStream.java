package ch20.ex03;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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

	public static void main(String[] args) throws IOException {
		byte[] source = "Test encryption and decryption!".getBytes();
		//Set Key
		byte key = (byte) 1;
		//Encrypt
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try (EncryptOutputStream eout = new EncryptOutputStream(byteArrayOutputStream, key)) {
			eout.write(source);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] encrypted = byteArrayOutputStream.toByteArray();
		//Decrypt
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(encrypted);
		byte[] decrypted = new byte[source.length];
		try(DecryptInputStream din = new DecryptInputStream(byteArrayInputStream, key)) {
//			din.read(decrypted);
			din.read(decrypted, 0, source.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Plaintext      : " + new String(source));
		System.out.println("Encrypted Text : " + new String(encrypted));
		System.out.println("Eecrypted Text : " + new String(decrypted));
	}

}
