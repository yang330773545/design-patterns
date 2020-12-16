package main;

/**
 * 
 * @author 50000008
 *
 * HolderNaive的同步版本 线程安全 会花费额外的同步开销
 */
public class HolderThreadSafe {

	private Heavy heavy;
	
	public HolderThreadSafe() {
		System.out.println("HolderNaive created!");
	}
	
	public synchronized Heavy getHeavy() {
		
		if(heavy == null) {
			heavy = new Heavy();
		}
		
		return heavy;
	}
	
}
