package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImperativeProgramming {

	private ImperativeProgramming() {}
	
	// 2000 年后的汽车模型
	public static List<String> getModelsAfter2000(List<Car> cars){
		List<Car> carsSortedByYear = new ArrayList<>();
		
		for(Car car : cars) {
			if(car.getYear() > 2000) {
				carsSortedByYear.add(car);
			}
		}
		
		Collections.sort(carsSortedByYear, new Comparator<Car>() {
			@Override
			public int compare(Car car1, Car car2) {
				return car1.getYear() - car2.getYear();
			}
		});
		
		List<String> models = new ArrayList<>();
	    for (Car car : carsSortedByYear) {
	        models.add(car.getModel());
	    }

	    return models;
	}
	
	// 对汽车进行分组
	public static Map<Category, List<Car>> getGroupingOfCarsByCategory(List<Car> cars){
		Map<Category, List<Car>> groupingByCategory = new HashMap<>();
	    for (Car car : cars) {
	        if (groupingByCategory.containsKey(car.getCategory())) {
	        	groupingByCategory.get(car.getCategory()).add(car);
	        } else {
		        List<Car> categoryCars = new ArrayList<>();
		        categoryCars.add(car);
		        groupingByCategory.put(car.getCategory(), categoryCars);
	        }
	    }
	    return groupingByCategory;
	}
	
	// 获得人的SEDAN车 且排序
	public static List<Car> getSedanCarsOwnedSortedByDate(List<Person> persons){
		List<Car> cars = new ArrayList<>();
	    for (Person person : persons) {
	        cars.addAll(person.getCars());
	    }

	    List<Car> sedanCars = new ArrayList<>();
	    for (Car car : cars) {
	        if (Category.SEDAN.equals(car.getCategory())) {
	        	sedanCars.add(car);
	        }
	    }

	    sedanCars.sort(new Comparator<Car>() {
	        @Override
	        public int compare(Car o1, Car o2) {
	        	return o1.getYear() - o2.getYear();
	        }
	    });

	    return sedanCars;
	}
}
