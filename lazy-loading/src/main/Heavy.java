package main;

// 创建此对象的成本高昂
public class Heavy {

	public Heavy() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		    System.out.println("Exception caught. " + e);
		}
		System.out.println("... Heavy created");
	}
}
