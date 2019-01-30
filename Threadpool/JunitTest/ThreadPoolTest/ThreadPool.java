/*
 * Copyright (C) 2012, 2013, 2016 RICOH Co., Ltd. All rights reserved.
 */
package ThreadPoolTest;

import java.util.ArrayList;
import java.util.List;


/**
 * Simple Thread Pool class.
 *
 * This class can be used to dispatch an Runnable object to
 * be exectued by a thread.
 *
 * [Instruction]
 *  Implement one constructor and three methods.
 *  Don't forget to write a Test program to test this class.
 *  Pay attention to @throws tags in the javadoc.
 *  If needed, you can put "synchronized" keyword to methods.
 *  All classes for implementation must be private inside this class.
 *  Don't use java.util.concurrent package.
 */
public class ThreadPool {
	private List<Runnable> queue = new ArrayList<Runnable>();

	/**
	 * すべてのスレッドが実行中：true
	 * ひとつでも空きがある状態:false
	 */
	private boolean isActive = false;

	private List<DispatchThread> threadList = new ArrayList<DispatchThread>();
	private int queueSize;
	private int numberOfThreads;

	/**
	 * Constructs ThreadPool.
	 *
	 * @param queueSize the max size of queue
	 * @param numberOfThreads the number of threads in this pool.
	 *
	 * @throws IllegalArgumentException if either queueSize or numberOfThreads
	 *         is less than 1
	 */
	public ThreadPool(int queueSize, int numberOfThreads) {
		if((queueSize < 1) || (numberOfThreads < 1)) {
			throw new IllegalArgumentException("queueSize and number of thread is inappropriate");
		}
		this.queueSize = queueSize;
		this.numberOfThreads = numberOfThreads;
	}

	/**
	 * Starts threads.
	 *
	 * @throws IllegalStateException if threads has been already started.
	 */
	public void start() {
		synchronized(this) {
			if(this.isActive) {
				throw new IllegalStateException("threads has been already started");
			}

			// threadの最大数までthreadを作成
			for(int i = 0; i < this.numberOfThreads; i ++) {
				this.threadList.add(new DispatchThread(queue));
			}

			// threadListの中身を順番にstartしていく。
			// startするときにロックを取ることで、同時に複数threadがstartされないように制御
			for(int i = 0; i < this.numberOfThreads; i ++) {
				synchronized(this.threadList) {
					this.threadList.get(i).start();
				}
			}
			this.isActive = true;
		}
	}

	/**
	 * Stop all threads and wait for their terminations.
	 *
	 * @throws IllegalStateException if threads has not been started.
	 */
	public void stop() {
		synchronized(this) {
			if(!this.isActive) {
				throw new IllegalStateException("threads has not been started.");
			}

			for(int i = 0; i < this.numberOfThreads; i ++) {
				this.threadList.get(i).stopThread();
			}
			this.isActive = false;
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

	/* dispathメソッドの引数で渡されたRunnableオブジェクトをqueueの中に詰めていく。
	 * queueに詰めるときにロックを取る必要がある？
	 *
	 * */
	public synchronized void dispatch(Runnable runnable) {
		if(runnable == null) {
			throw new AssertionError("Not Implemented Yet");
		}
		if(!this.isActive) {
			throw new IllegalStateException("ThreadPool has not been started");
		}

		// queueの中身が最大値以上の場合は無限ループを行って処理されるまで待つ
		while(this.queue.size() >= this.queueSize) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// queueの中身が最大値未満の場合に以下の処理を行う
		// queueにRunnableオブジェクトを追加する。この時にロックを取得する。
		synchronized(this.queue) {
			this.queue.add(runnable);
		}
	}

	private static class DispatchThread extends Thread {
		private List<Runnable> queue;

		public DispatchThread(List<Runnable> queue) {
			this.queue = queue;
		}

		public void run() {

		}

		public void stopThread() {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
