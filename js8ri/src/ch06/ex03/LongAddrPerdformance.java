package ch06.ex03;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongAddrPerdformance {

	private static final int THREAD_NUM = 1000;
	private static final int REPEAT_NUM = 10000;

	public static void main(String[] args) {
		// AtomicLong
		AtomicLong atomicLogn = new AtomicLong();
		Thread[] atomicThreads = new Thread[THREAD_NUM];
		for (int i = 0; i < atomicThreads.length; i++) {
			atomicThreads[i] = new Thread(() ->{
				for (int j = 0; j < REPEAT_NUM; j++) {
					atomicLogn.incrementAndGet();
				}
			});
		}
		long start = System.currentTimeMillis();
		for (Thread thread : atomicThreads) {
			thread.start();
		}
		for (Thread thread : atomicThreads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("AtomicLong time:" + (end - start) + "[ms], Result:" + atomicLogn.get() );

		//LongAddr
		LongAdder longAddr = new LongAdder();
		Thread[] addrThread = new Thread[THREAD_NUM];
		for (int i = 0; i < addrThread.length; i++) {
			addrThread[i] = new Thread(() -> {
				for (int j = 0; j < REPEAT_NUM; j++) {
					longAddr.increment();
				}
			});
		}
		start = System.currentTimeMillis();
		for (Thread thread : addrThread) {
			thread.start();
		}
		for (Thread thread : addrThread) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		end = System.currentTimeMillis();
		System.out.println("LongAddr time:\t" + (end - start) + "[ms], Result:" + longAddr.sum() );
	}
}
