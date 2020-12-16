package main;

import java.io.IOException;

public class App {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		RainbowFish fishV1 = new RainbowFish("Zed", 10, 11, 12);
		
		// 序列化操作
		RainbowFishSerializer.writeV1(fishV1, "fish1.out");
		
		// 读取
		RainbowFish deserializedRainbowFishV1 = RainbowFishSerializer.readV1("fish1.out");
		
		System.out.println(deserializedRainbowFishV1.getName() + " " + deserializedRainbowFishV1.getAge());
		
		RainbowFishV2 fishV2 = new RainbowFishV2("Scar", 5, 12, 15, true, true, true);
		RainbowFishSerializer.writeV2(fishV2, "fish2.out");
		RainbowFish deserializedFishV2 = RainbowFishSerializer.readV1("fish2.out");
		
		System.out.println(deserializedFishV2.getName() + " " + deserializedFishV2.getAge());
	}
}
