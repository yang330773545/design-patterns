package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

public class App {

	public static void main(String[] args) {
		 ExecutorService executeService = Executors.newFixedThreadPool(10);
		 ReadWriteLock lock = new ReaderWriterLock();
		 
		 for(int i=0; i < 5; i++) {
			 // 与当前线程隔离的随机数生成器 生成long
			 long writingTime = ThreadLocalRandom.current().nextLong(5000);
		     executeService.submit(new Writer("Writer " + i, lock.writeLock(), writingTime));
		 }
		 
		 System.out.println("Writers added...");
		 
		 for(int i=0; i < 5; i++) {
			 long readingTime = ThreadLocalRandom.current().nextLong(10);
			 executeService.submit(new Reader("Reader " + i, lock.readLock(), readingTime));
		 }
		 
		 System.out.println("Readers added...");
		 
		 try {
			 Thread.sleep(5000L);
		 } catch (InterruptedException e) {
			 System.out.println("Error sleeping before adding more readers" + e);
		     Thread.currentThread().interrupt();
		 }
		 
		 for (int i = 6; i < 10; i++) {
			 long readingTime = ThreadLocalRandom.current().nextLong(10);
			 executeService.submit(new Reader("Reader " + i, lock.readLock(), readingTime));
		 }
		 System.out.println("More readers added...");
		 
		 executeService.shutdown();
		 
		 try {
			 // 阻止所有任务在关闭请求完成后执行，或发生超时，或当前线程中断，以先到者为准。
			 executeService.awaitTermination(5, TimeUnit.SECONDS);
		 } catch(InterruptedException e) {
			 System.out.println("Error waiting for ExecutorService shutdown" + e);
		     Thread.currentThread().interrupt();
		 }
	}
}
