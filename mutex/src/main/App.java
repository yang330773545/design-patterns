package main;

/**
 * 互斥锁模式
 */
public class App {
	
	public static void main(String[] args) {
		Mutex mutex = new Mutex();
		Jar jar = new Jar(1000, mutex);
		Thief peter = new Thief("Peter", jar);
		Thief john = new Thief("John", jar);
	    peter.start();
	    john.start();
	}
}
