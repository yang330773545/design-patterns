package main;

/**
 * 
 * @author 50000008
 *
 *	懒加载非线程安全的简单实现
 */
public class HolderNaive {

	private Heavy heavy;
	
	public HolderNaive() {
		System.out.println("HolderNaive created!");
	}
	
	public Heavy getHeavy() {
		
		if(heavy == null) {
			heavy = new Heavy();
		}
		
		return heavy;
	}
}
