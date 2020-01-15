package ch14.ex09;

import java.util.Collections;
import java.util.Random;

public class ThreadGroupViewer {

	private final int INTERVAL = 2000;
	private final int MARGIN = 10;
	private final ThreadGroup threadGroup;

	public ThreadGroupViewer(ThreadGroup threadGroup) {
		this.threadGroup = threadGroup;
	}

	public void start() {
		for(;;) {
			System.out.println("---------active thread---------");
			showGroup(threadGroup, 0);
			try {
				Thread.sleep(INTERVAL);
			} catch (InterruptedException e) {
			}
		}
	}

	private void showGroup(ThreadGroup topGroup, int hierarchy) {
		int activeGroupCount = topGroup.activeGroupCount() + MARGIN;
		ThreadGroup[] groups = new ThreadGroup[activeGroupCount];
		topGroup.enumerate(groups);
		System.out.print(String.join("", Collections.nCopies(hierarchy, "\t")) +  topGroup.getName() + " : ");
		showThread(topGroup);
		System.out.println();
		for (ThreadGroup threadGroup : groups) {
			if (threadGroup == null) {
				break;
			} else if (topGroup.equals(threadGroup) || !threadGroup.getParent().equals(topGroup)) {
				continue;
			}
			showGroup(threadGroup, hierarchy+1);
		}
	}

	private void showThread(ThreadGroup group) {
		int activeThreadCount = group.activeCount() + MARGIN;
		Thread[] threads = new Thread[activeThreadCount];
		group.enumerate(threads);
		for (Thread thread : threads) {
			if (thread == null) {
				break;
			} else if (thread.getThreadGroup().equals(group)) {
				System.out.print(thread.getName() + " ");
			}
		}
	}

	public static void main(String[] args) {
		ThreadGroup group = new ThreadGroup("parent");
		ThreadGroup group2 = new ThreadGroup(group, "child1");
		ThreadGroup group3 = new ThreadGroup(group, "child2");
		ThreadGroup group21 = new ThreadGroup(group2, "child11");
		ThreadGroup group22 = new ThreadGroup(group2, "child12");
		ThreadGroup group31 = new ThreadGroup(group3, "child21");
		Thread[] threads = new Thread[] {
				new Thread(group, new RandomDeleteThread()),
				new Thread(group, new RandomDeleteThread()),
				new Thread(group3, new RandomDeleteThread()),
				new Thread(group2, new RandomDeleteThread()),
				new Thread(group21, new RandomDeleteThread()),
				new Thread(group22, new RandomDeleteThread()),
				new Thread(group31, new RandomDeleteThread()),
		};
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		new ThreadGroupViewer(group).start();
	}

	public static class RandomDeleteThread implements Runnable{

		public void run() {
			int random = new Random().nextInt(20)*1000;
			try {
				Thread.sleep(random);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
