package main;

public class Jar {

	// 锁
	private Lock lock;
	
	// 资源 访问此资源必须获得锁
	private int beans;
	
	public Jar(int beans, Lock lock) {
		this.beans = beans;
	    this.lock = lock;
	}
	
	 public boolean takeBean() {
		 boolean success = false;
	     try {
	    	 lock.acquire();
		     success = beans > 0;
		     if (success) {
		       beans = beans - 1;
		     }
	     } catch (Exception e) {
	    	 e.printStackTrace();
	     } finally {
	    	 lock.release();
	     }
	     
	     return success;
	 }
	
}
