/*
 * Copyright (C) 2012, 2013 RICOH Co., Ltd. All rights reserved.
 * Copyright (C) 2019 Yoshiki Shibata. All rights reserved.
 */
package ch14.ex10;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Simple Thread Pool class.
 *
 * This class can be used to dispatch an Runnable object to
 * be exectued by a thread.<br><br>
 *
 * [Instruction]
 * <ul>
 *  <li> Implement one constructor and three methods. </li>
 *  <li> Don't forget to write a Test program to test this class. </li>
 *  <li> Pay attention to @throws tags in the javadoc. </li>
 *  <li> If needed, you can put "synchronized" keyword to methods. </li>
 *  <li> All classes for implementation must be private inside this class. </li>
 *  <li> Don't use java.util.concurrent package. </li>
 *  <li> Don't use {@link java.lang.Thread#interrupt}  method to stop a thread</li>
 *  </ul>
 *
 *  @author Yoshiki Shibata
 */
public class ThreadPool {

	private int queueSize;
	private int numberOfthread;
	private int usedThreadCount = 0;
	private boolean isStarted = false;
	private List<Runnable> taskQueue = new ArrayList<>();
	private Object poolLock = new Object();
	private QueueManager queueManager = new QueueManager();
	private int count = 0;

	/**
     * Constructs ThreadPool.
     *
     * @param queueSize the max size of queue
     * @param numberOfThreads the number of threads in this pool.
     *
     * @throws IllegalArgumentException if either queueSize or numberOfThreads
     *         is less than 1
     */
    ThreadPool(int queueSize, int numberOfThreads) {
        if (queueSize <= 0 || numberOfThreads <= 0) {
			throw new IllegalArgumentException();
		}
        this.queueSize = queueSize;
        this.numberOfthread = numberOfThreads;
    }

    /**
     * Starts threads.
     *
     * @throws IllegalStateException if threads has been already started.
     */
    public synchronized void start() {
        if (isStarted) {
			throw new IllegalStateException();
		}
        isStarted = true;
        queueManager.start();
    }

    /**
     * Stop all threads gracefully and wait for their terminations.
	 * All requests dispatched before this method is invoked must complete
	 * and this method also will wait for their completion.
     *
     * @throws IllegalStateException if threads has not been started.
     */
    public void stop() {
        if (!isStarted) {
			throw new IllegalStateException();
		}
        queueManager.signalEnd();
//        try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//		}
    }

    /**
     * Executes the specified Runnable object, using a thread in the pool.
     * run() method will be invoked in the thread. If the queue is full, then
     * this method invocation will be blocked until the queue is not full.
     *
     * @param runnable Runnable object whose run() method will be invoked.
     *
     * @throws NullPointerException if runnable is null.
     * @throws IllegalStateException if this pool has not been started yet.
     */
    public synchronized void dispatch(Runnable runnable) {
        if (!isStarted) {
        	throw new IllegalStateException();
		}
        Objects.requireNonNull(runnable);
        queueManager.signalPutQueue(runnable);
    }

    private class QueueManager extends Thread{

    	private boolean isEnd = false;

		@Override
		public void run() {
			while(!isEnd) {
				System.out.println("manager run " + usedThreadCount +" " + numberOfthread + " " + isEnd);
				waitQueue();
				if (usedThreadCount < numberOfthread && !isEnd) {
					executeQueueRunnable();
					count++;
				}
				System.out.println(count);
			}

		}

		private synchronized void waitQueue() {
			try {
				wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		private synchronized void executeQueueRunnable() {
			if (taskQueue.size() > 0) {
				Thread t = new Thread(new Worker(taskQueue.get(0),this));
				t.start();
				usedThreadCount++;
			}
		}

		public synchronized void signalPutQueue(Runnable task) {
			while(true) {
				if (taskQueue.size() < queueSize) {
					taskQueue.add(task);
					break;
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
			notifyAll();
		}

		public synchronized void signalEndWorker() {
			usedThreadCount--;
			notifyAll();
		}

		public synchronized void signalEnd() {
			isEnd = true;
			notifyAll();
		}

		private class Worker implements Runnable{

			private Runnable task;
			private QueueManager manager;

			public Worker(Runnable task, QueueManager manager) {
				this.task = task;
				this.manager = manager;
			}

			@Override
			public void run() {
				System.out.println("run task");
				task.run();
				manager.signalEndWorker();
			}

		}

    }
}