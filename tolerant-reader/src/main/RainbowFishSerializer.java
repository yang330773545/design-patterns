package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author 50000008
 *
 * 使用文件和map进行序列化，即使添加新属性阅读也不会中断
 */
public class RainbowFishSerializer {
	public static final String LENGTH_METERS = "lengthMeters";
	public static final String WEIGHT_TONS = "weightTons";

	private RainbowFishSerializer() {}
	
	public static void writeV1(RainbowFish rainbowFish, String filename) throws IOException {
	    Map<String, String> map = new HashMap<>(); 
	    
	    map.put("name", rainbowFish.getName());
	    map.put("age", String.format("%d", rainbowFish.getAge()));
	    map.put(LENGTH_METERS, String.format("%d", rainbowFish.getLengthMeters()));
	    map.put(WEIGHT_TONS, String.format("%d", rainbowFish.getWeightTons()));

	    try (FileOutputStream fileOut = new FileOutputStream(filename);ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {
	    	objOut.writeObject(map);
	    }
	}
	
	public static void writeV2(RainbowFishV2 rainbowFish, String filename) throws IOException {
		Map<String, String> map = new HashMap<>(); 
		map.put("name", rainbowFish.getName());
	    map.put("age", String.format("%d", rainbowFish.getAge()));
	    map.put(LENGTH_METERS, String.format("%d", rainbowFish.getLengthMeters()));
	    map.put(WEIGHT_TONS, String.format("%d", rainbowFish.getWeightTons()));
	    map.put("angry", Boolean.toString(rainbowFish.isAngry()));
	    map.put("hungry", Boolean.toString(rainbowFish.isHungry()));
	    map.put("sleeping", Boolean.toString(rainbowFish.isSleeping()));

	    try (FileOutputStream fileOut = new FileOutputStream(filename); ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {
	    	objOut.writeObject(map);
	    }
	}
	
	
	public static RainbowFish readV1(String filename) throws IOException, ClassNotFoundException {
		Map<String, String> map;

	    try (FileInputStream fileIn = new FileInputStream(filename); ObjectInputStream objIn = new ObjectInputStream(fileIn)) {
	    	map = (Map<String, String>) objIn.readObject();
	    }

	    return new RainbowFish(
    		map.get("name"),
    		Integer.parseInt(map.get("age")),
    		Integer.parseInt(map.get("lengthMeters")),
    		Integer.parseInt(map.get(WEIGHT_TONS))
	    );
	}

}
