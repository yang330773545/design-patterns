package main;

// 互斥锁的实现
public class Mutex implements Lock {
	
	// 锁的当前拥有者
	private Object owner;
	
	public Object getOwner() {
		return owner;
	}

	@Override
	public synchronized void acquire() throws InterruptedException {
		// TODO Auto-generated method stub
		while(owner != null) {
			wait();
		}
		owner = Thread.currentThread();
	}

	@Override
	public synchronized void release() {
		// TODO Auto-generated method stub
		if (Thread.currentThread() == owner) {
			owner = null;
			notify();
		}

	}

}
