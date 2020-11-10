package ch03.ex17;

import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.Test;

class DoParallelAsyncTest {

	@Test
	void testSuccess() {

		CountDownLatch latch = new CountDownLatch(2);
		DoParallelAsync.doInParallelAsync(()->latch.countDown(), ()->latch.countDown(), (e)->e.printStackTrace());
		try {
			latch.await();
			assertEquals(0, latch.getCount());
		} catch (InterruptedException e1) {
			fail();
		}
	}

	@Test
	void testFirstError() {

		CountDownLatch latch = new CountDownLatch(2);
		Integer exeption = null;
		// NullPointerException
		DoParallelAsync.doInParallelAsync(()->exeption.byteValue(), ()->latch.countDown(), (e)->latch.countDown());
		try {
			latch.await();
			assertEquals(0, latch.getCount());
		} catch (InterruptedException e1) {
			fail();
		}
	}

	@Test
	void testSecondError() {

		CountDownLatch latch = new CountDownLatch(2);
		Integer exeption = null;
		// NullPointerException
		DoParallelAsync.doInParallelAsync(()->latch.countDown(), ()->exeption.byteValue(), (e)->latch.countDown());
		try {
			latch.await();
			assertEquals(0, latch.getCount());
		} catch (InterruptedException e1) {
			fail();
		}
	}

	@Test
	void testBothError() {

		CountDownLatch latch = new CountDownLatch(2);
		Integer exeption = null;
		// NullPointerException
		DoParallelAsync.doInParallelAsync(()->exeption.byteValue(), ()->exeption.byteValue(), (e)->latch.countDown());
		try {
			latch.await();
			assertEquals(0, latch.getCount());
		} catch (InterruptedException e1) {
			fail();
		}
	}

}
