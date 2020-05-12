package ch14.ex02;

import org.junit.Test;

public class PrintServerTest {

	@Test (expected = IllegalStateException.class)
	public void testNG() {
		PrintServer printServer = new PrintServer();
		printServer.run();
	}

	@Test
	public void testOK() {
		PrintServer printServer = new PrintServer();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}

}
