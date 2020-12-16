package main;

import java.util.List;

// 一个人有多个车
public class Person {

	private final List<Car> cars;
	
	public Person(List<Car> cars) {
	    this.cars = cars;
	}

	public List<Car> getCars() {
	    return cars;
	}
}
