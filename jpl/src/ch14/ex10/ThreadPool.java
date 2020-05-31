/*
 * Copyright (C) 2012, 2013 RICOH Co., Ltd. All rights reserved.
 * Copyright (C) 2019 Yoshiki Shibata. All rights reserved.
 */
package ch14.ex10;

import java.util.Collections;
import java.util.LinkedList;
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

	private int maxQueueSize;
	private boolean isStarted = false;
	private boolean isThreadStarted = false;
	private boolean isShutdown = false;
	private List<Runnable> requestQueue = Collections.synchronizedList(new LinkedList<>());
	private final Worker[] threadPool;
	private boolean callDispatch = false;

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
        System.out.println("######Create Thread Pool ######");
    	System.out.println("Thread Num : " + numberOfThreads + " Queue Size : " + queueSize);
        this.maxQueueSize = queueSize;
        threadPool = new Worker[numberOfThreads];
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
        for (int i = 0; i < threadPool.length; i++) {
			threadPool[i] = new Worker(this, "thread-" + i);
		}
        if (!isThreadStarted) {
			for (int i = 0; i < threadPool.length; i++) {
				threadPool[i].start();
			}
			isThreadStarted = true;
		}
    }

    /**
     * Stop all threads gracefully and wait for their terminations.
	 * All requests dispatched before this method is invoked must complete
	 * and this method also will wait for their completion.
     *
     * @throws IllegalStateException if threads has not been started.
     */
    public void stop() {

    	synchronized (this) {
	    	if (!isStarted) {
				throw new IllegalStateException();
			}
	        if (isShutdown) {
				throw new IllegalStateException();
			}
	        isShutdown = true;
    	}

    	System.out.println("Call stop");
        //タスクがないことを確認
        synchronized (this) {
            while(requestQueue.size() != 0) {
        		try {
        			System.out.println("Wait to finish remaining tasks");
    				wait();
    			} catch (InterruptedException e) {
    			}
        	}
		}

        //終了通知
        for (int i = 0; i < threadPool.length; i++) {
        	threadPool[i].signalStop();
		}

        for (int i = 0; i < threadPool.length; i++) {
			try {
				synchronized (this) {
					notifyAll();
				}
				if (!callDispatch) {
					threadPool[i].interrupt();
				}
				threadPool[i].join();
			} catch (InterruptedException e) {
			}
		}
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
        System.out.println("Dispatch task");
        callDispatch = true;
        Objects.requireNonNull(runnable);
        while (maxQueueSize <= requestQueue.size()) {
			try {
				System.out.println("Wait dispatch until task taken.");
				wait();
			} catch (InterruptedException e) {
			}
		}
		requestQueue.add(runnable);
		notifyAll();
    }

    public synchronized Runnable takeTask() {

		if (requestQueue.size() == 0 && !isShutdown) {
			try {
				System.out.println("Wait to take task until task dispatched. [" + Thread.currentThread().getName() + "]" );
				wait();
			} catch (InterruptedException e) {
			}
		}
		Runnable task = null;
		if (requestQueue.size() != 0) {
			task = requestQueue.remove(0);
		}
		notifyAll();
		return task;
	}

    private static class Worker extends Thread{

		private ThreadPool threadPool;
		private boolean isWork = true;
		Runnable task;

		public Worker(ThreadPool threadPool, String name) {
			super(name);
			this.threadPool = threadPool;
		}

		@Override
		public synchronized void run() {
			while(isWork) {
				System.out.println("Take task [" + this.getName() + "]");
				task = threadPool.takeTask();
				if (task != null) {
					task.run();
				}
			}
			System.out.println("End worker : " + this.getName());
		}

		public void signalStop() {
			isWork = false;
		}

	}
}