package main;

import java.util.concurrent.locks.Lock;

public class Reader implements Runnable {


	private final Lock readLock;

	private final String name;

	private final long readingTime;
	
	public Reader(String name, Lock readLock, long readingTime) {
	    this.name = name;
	    this.readLock = readLock;
	    this.readingTime = readingTime;
	}

	public Reader(String name, Lock readLock) {
		this(name, readLock, 250L);
	}

	  
	@Override
	public void run() {
		// TODO Auto-generated method stub
		readLock.lock();
		try {
			read();
		} catch(InterruptedException e) {
			System.out.println("读取时出现了异常！");
			// 中断当前线程
			Thread.currentThread().interrupt();
		} finally {
			readLock.unlock();
		}
		
	}
	
	public void read() throws InterruptedException {
		System.out.println(name + " 开始");
		Thread.sleep(readingTime);
		System.out.println(name+" 在 "+ readingTime + "ms 完成。");
	}

}
