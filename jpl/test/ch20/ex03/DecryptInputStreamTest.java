package ch20.ex03;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

public class DecryptInputStreamTest {

	@Test
	public void test() {
		String plainText = "Test encryption and decryption!";
		byte[] source = plainText.getBytes();
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
		System.out.println("Encrypted Text : " + new String(encrypted));
		//Decrypt
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(encrypted);
		byte[] decrypted = new byte[source.length];
		try(DecryptInputStream din = new DecryptInputStream(byteArrayInputStream, key)) {
			din.read(decrypted, 0, source.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(plainText, new String(decrypted));
	}

}
