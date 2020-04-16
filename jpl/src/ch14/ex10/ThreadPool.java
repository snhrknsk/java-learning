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
	static Object lock = new Object();

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
    	System.out.println("######Create Thread Pool######");
        if (queueSize <= 0 || numberOfThreads <= 0) {
			throw new IllegalArgumentException();
		}
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
    }

    /**
     * Stop all threads gracefully and wait for their terminations.
	 * All requests dispatched before this method is invoked must complete
	 * and this method also will wait for their completion.
     *
     * @throws IllegalStateException if threads has not been started.
     */
    public synchronized void stop() {

    	if (!isStarted) {
			throw new IllegalStateException();
		}

        System.out.println("Call stop");
        if (isShutdown) {
			throw new IllegalStateException();
		}
        isShutdown = true;

        //タスクがすべて終わるまでwait, takeTaskからnotifyでrequestが空であれば終了
        int waitCount = 0;
        while(waitCount != threadPool.length && isThreadStarted) {
        	waitCount = 0;
        	for (int i = 0; i < threadPool.length; i++) {
    			if (threadPool[i].getState() == Thread.State.WAITING || threadPool[i].getState() == Thread.State.TERMINATED && requestQueue.size() == 0) {
    				waitCount++;
    			} else {
    				System.out.println("Executing thread " + i);
    				break;
    			}
    		}
        	System.out.println(waitCount +"#"+ threadPool.length);
        	if (waitCount != threadPool.length) {
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}
//        	System.out.println(waitCount +":"+ threadPool.length);
        }

        for (int i = 0; i < threadPool.length; i++) {
        	System.out.println("notify stop " + i);
			threadPool[i].signalStop();
		}

        for (int i = 0; i < threadPool.length; i++) {
			try {
				System.out.println("Notify end " + i);
				threadPool[i].signalNotify();
				System.out.println("Notify join " + i);
				threadPool[i].join();
			} catch (InterruptedException e) {
			}
		}
        System.out.println("End All Thread");
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
        if (!isThreadStarted) {
			for (int i = 0; i < threadPool.length; i++) {
				threadPool[i].start();
			}
			isThreadStarted = true;
		}
        while (maxQueueSize <= requestQueue.size()) {
			try {
				System.out.println("Wait for worker executing");
				wait();
			} catch (InterruptedException e) {
			}
		}
		requestQueue.add(runnable);
		notifyWaitingWorker();
    }

    public synchronized Runnable takeTask() {

    	System.out.println("Queue num " + requestQueue.size());
		if (requestQueue.size() == 0 || requestQueue.get(0) == null) {
			return null;
		}
		Runnable task = requestQueue.remove(0);
		notifyAll();
		return task;
	}

    public synchronized void endTask() {
    	if (isShutdown && requestQueue.size() == 0) {
    		System.out.println("##notify end task");
        	notifyAll();
		}
    }

    public void notifyWaitingWorker() {
		for (int i = 0; i < threadPool.length; i++) {
			if (threadPool[i].getState() == Thread.State.WAITING) {
				threadPool[i].signalNotify();
				return;
			}
			threadPool[i].signalAddWorkWhileRun();
		}
		System.out.println("No waiting thread");
	}

    public synchronized void notifyAllThread() {
		for (int i = 0; i < threadPool.length; i++) {
			threadPool[i].signalNotify();
		}
	}


    private static class Worker extends Thread{

		private ThreadPool threadPool;
		private boolean isWork = true;
		private boolean isWorking = false;

		public Worker(ThreadPool threadPool, String name) {
			super(name);
			this.threadPool = threadPool;
		}

		@Override
		public synchronized void run() {
			System.out.println("Start work");
			Runnable task;
			while(isWork) {
				System.out.println("Takeing task " + this.getName());
				task = threadPool.takeTask();
				if (task != null) {
//					System.out.println("Run worker " + this.getName() + " Waiting interrupt " + isWorking);
					task.run();
//					threadPool.endTask();
					continue;
				} else {
					System.out.println("No worker " + this.getName());
					isWorking = false;
				}
				try {
					if (isWorking) {
						continue;
					} else if (isWork) {
						System.out.println("Wait worker " + this.getName());
						threadPool.endTask();
						wait();
						continue;
					}
					break;
//					System.out.println("ARIEN");
				} catch (InterruptedException e) {
					System.out.println("Interrupted " + this.getName());
				}
			}
			System.out.println("End worker : " + this.getName());
		}

		public void signalStop() {
			isWork = false;
		}

		public synchronized void signalNotify() {
			System.out.println("Notify worker " + this.getName() + " Stop " + isWork);
			notifyAll();
		}

		public void signalAddWorkWhileRun() {
			isWorking = true;
		}

	}
}