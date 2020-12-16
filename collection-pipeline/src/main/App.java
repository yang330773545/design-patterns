package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {

	public static void main(String[] args) {
		List<Car> cars = CarFactory.createCars();

	    List<String> modelsImperative = ImperativeProgramming.getModelsAfter2000(cars);
	    System.out.println(modelsImperative.toString());

	    List<String> modelsFunctional = FunctionalProgramming.getModelsAfter2000(cars);
	    System.out.println(modelsFunctional.toString());

	    Map<Category, List<Car>> groupingByCategoryImperative = ImperativeProgramming.getGroupingOfCarsByCategory(cars);
	    System.out.println(groupingByCategoryImperative.toString());

	    Map<Category, List<Car>> groupingByCategoryFunctional = FunctionalProgramming.getGroupingOfCarsByCategory(cars);
	    System.out.println(groupingByCategoryFunctional.toString());

	    Person john = new Person(cars);
	    List<Person> persons = new ArrayList<>();
	    persons.add(john);

	    List<Car> sedansOwnedImperative = ImperativeProgramming.getSedanCarsOwnedSortedByDate(persons);
	    System.out.println(sedansOwnedImperative.toString());

	    List<Car> sedansOwnedFunctional = FunctionalProgramming.getSedanCarsOwnedSortedByDate(persons);
	    System.out.println(sedansOwnedFunctional.toString());
	}
}
