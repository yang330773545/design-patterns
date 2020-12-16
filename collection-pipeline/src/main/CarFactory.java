package main;

import java.util.ArrayList;
import java.util.List;

public class CarFactory {

	private CarFactory() {}
	
	public static List<Car> createCars() {
		List<Car> cars = new ArrayList<>(); 
		cars.add(new Car("Jeep", "Wrangler", 2011, Category.JEEP));
		cars.add(new Car("Jeep", "Comanche", 1990, Category.JEEP));
		cars.add(new Car("Dodge", "Avenger", 2010, Category.SEDAN));
		cars.add(new Car("Buick", "Cascada", 2016, Category.CONVERTIBLE));
		cars.add(new Car("Ford", "Focus", 2012, Category.SEDAN));
		cars.add(new Car("Chevrolet", "Geo Metro", 1992, Category.CONVERTIBLE));
		return cars;
	}
}
