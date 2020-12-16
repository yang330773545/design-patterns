package main;

public class Car {
	private String make;
	private String model;
	private int year;
	private Category category;
	
	public Car(String make, String model, int year, Category category) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.category = category;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}

	public Category getCategory() {
		return category;
	}
	
	public int hashCode() {
		final int prime =31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + year;
		return result;
	}
	
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null) return false;
		if(getClass() != obj.getClass()) return false;
		Car other = (Car) obj;
		if(category != other.category) return false;
		if(make == null) {
			if(other.make != null) return false;
		} else if(!make.equals(other.make)) {
			return false;
		}
		if (model == null) {
	        if (other.model != null) {
	        	return false;
	        }
	    } else if (!model.equals(other.model)) {
	        return false;
	    }
	    return year == other.year;
	}
}
