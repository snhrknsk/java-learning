package ch03.ex02;

import java.util.concurrent.locks.ReentrantLock;


public class Lock {

	public static void waitLock( ReentrantLock lock, Runnable run) {
		lock.lock();
		try {
			run.run();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		waitLock(lock, () -> {
			System.out.println("Locked " + lock.isLocked());
		});
	}
}
