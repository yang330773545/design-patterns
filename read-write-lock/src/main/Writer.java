package main;

import java.util.concurrent.locks.Lock;

public class Writer implements Runnable {
	
	private final Lock writeLock;

	private final String name;

	private final long writingTime;
	
	public Writer(String name, Lock writeLock) {
		this(name, writeLock, 250L);
	}

	public Writer(String name, Lock writeLock, long writingTime) {
		this.name = name;
	    this.writeLock = writeLock;
	    this.writingTime = writingTime;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			write();
		} catch(InterruptedException e) {
			System.out.println("写入时出现了异常！");
			Thread.currentThread().interrupt();
		} finally {
			writeLock.unlock();
		}
		
	}

	public void write() throws InterruptedException {
		System.out.println(name + " begin");
		Thread.sleep(writingTime);
		System.out.println(name + "finished after writing " + writingTime + " ms.");
	}
}
