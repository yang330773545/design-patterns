package main;

public class App {

	// 跑进去开门 抢宝箱 资源获取即初始化模式可用于实施异常安全的资源管理。
	public static void main(String[] args) throws Exception {
		try (SlidingDoor ignored = new SlidingDoor()) {
			System.out.println("Walking in.");
		}

	    try (TreasureChest ignored = new TreasureChest()) {
	        System.out.println("Looting contents.");
	    }
	}
}
