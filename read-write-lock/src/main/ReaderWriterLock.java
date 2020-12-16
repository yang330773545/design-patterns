package main;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * 
 * @author 50000008
 * 允许多个读取器同时持有锁，但是如果有任何写入器持有锁，则
*  读者等待。如果读者持有锁，则作家等待。此锁不公平。
 *
 */
public class ReaderWriterLock implements ReadWriteLock {
	
	private final Object readerMutex = new Object();

	private int currentReaderCount;
	
	/**
	 * 全局互斥锁用于指示读取器或写入器是否立即获得了锁定。包含readerLock引用时表示读者获取了锁，另一个读者也可以同时执行读取操作
	 * 包含writerLock引用时说明作家获取了锁 没有其他读者或作家可以得到锁。
	 */
	private final Set<Object> globalMutex = new HashSet<>();

	private final ReadLock readerLock = new ReadLock();
	private final WriteLock writerLock = new WriteLock();

	@Override
	public Lock readLock() {
		// TODO Auto-generated method stub
		return readerLock;
	}

	@Override
	public Lock writeLock() {
		// TODO Auto-generated method stub
		return writerLock;
	}
	
	//当globalMutex持有writerLock的引用时，返回true。
	private boolean doesWriterOwnThisLock() {
		return globalMutex.contains(writerLock);
	}
	  
    private boolean isLockFree() {
    	return globalMutex.isEmpty();
	}

	// 无作家获得锁 则所有 阅读者可阅读
	private class ReadLock implements Lock {

		@Override
		public void lock() {
			// TODO Auto-generated method stub
			synchronized (readerMutex) {
				currentReaderCount++;
		        if (currentReaderCount == 1) {
		        	acquireForReaders();
		        }
		    }
			
		}
		
		private void acquireForReaders() {
			// Try to get the globalMutex lock for the first reader
		    synchronized (globalMutex) {
		        while (doesWriterOwnThisLock()) {
		        	try {
		        		globalMutex.wait();
		        	} catch (InterruptedException e) {
		        		String message = "InterruptedException while waiting for globalMutex in acquireForReaders";
		        		System.out.println(message + e);
		        		Thread.currentThread().interrupt();
		            }
		        }
		        globalMutex.add(this);
		      }
		  }

		@Override
		public void lockInterruptibly() throws InterruptedException {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public Condition newCondition() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean tryLock() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public void unlock() {
			// TODO Auto-generated method stub
			synchronized(readerMutex) {
				currentReaderCount--;
				if (currentReaderCount == 0) {
					synchronized (globalMutex) {
		            // Notify the waiter, mostly the writer
		            globalMutex.remove(this);
		            globalMutex.notifyAll();
		            }
				}
			}
		}
		
	}

	// 写锁只能一个作家同时访问
	private class WriteLock implements Lock{

		@Override
		public void lock() {
			// TODO Auto-generated method stub
			synchronized(globalMutex) {
				// 锁未被释放
				while(!isLockFree()) {
					try {
						globalMutex.wait();
					} catch(InterruptedException e) {
						System.out.println("InterruptedException while waiting for globalMutex to begin writing" + e);
						Thread.currentThread().interrupt();
					}
				}
				globalMutex.add(this);
			}
		}

		@Override
		public void lockInterruptibly() throws InterruptedException {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public Condition newCondition() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean tryLock() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public void unlock() {
			// TODO Auto-generated method stub
			synchronized (globalMutex) {
		        globalMutex.remove(this);
		        // Notify the waiter, other writer or reader
		        globalMutex.notifyAll();
		    }
		}
		
	}
}
