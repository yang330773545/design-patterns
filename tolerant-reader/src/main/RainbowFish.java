package main;

import java.io.Serializable;

// 初始概要数据
public class RainbowFish implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String name;
	private final int age;
	private final int lengthMeters;
	private final int weightTons;
	
	public RainbowFish(String name, int age, int lengthMeters, int weightTons) {
		this.name = name;
		this.age = age;
		this.lengthMeters = lengthMeters;
		this.weightTons = weightTons;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getLengthMeters() {
		return lengthMeters;
	}

	public int getWeightTons() {
		return weightTons;
	}

	
}
