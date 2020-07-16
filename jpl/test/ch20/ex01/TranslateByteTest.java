package ch20.ex01;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

public class TranslateByteTest {

	@Test
	public void test() {
		String source = "test input stream";
		String expected = "TesT inpuT sTream";

		ByteArrayInputStream inputStream = new ByteArrayInputStream(source.getBytes());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			new TranslateByte().translateByte((byte)'t', (byte)'T', inputStream, outputStream);
		} catch (IOException e) {
			fail();
		}
		assertEquals(expected, outputStream.toString());
	}

	@Test
	public void testSpace() {
		String source = "test input stream";
		String expected = "test,input,stream";

		ByteArrayInputStream inputStream = new ByteArrayInputStream(source.getBytes());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			new TranslateByte().translateByte((byte)' ', (byte)',', inputStream, outputStream);
		} catch (IOException e) {
			fail();
		}
		assertEquals(expected, outputStream.toString());
	}

}
