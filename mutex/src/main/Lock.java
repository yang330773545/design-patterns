package main;

// 获取和释放锁 接口
public interface Lock {
	
	void acquire() throws InterruptedException;

	void release();
}
