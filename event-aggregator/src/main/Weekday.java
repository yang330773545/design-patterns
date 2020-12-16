package main;

// 工作日枚举
public enum Weekday {
	MONDAY("Monday"),
	TUESDAY("Tuesday"),
	WEDNESDAY("Wednesday"),
	THURSDAY("Thursday"),
	FRIDAY("Friday"),
	SATURDAY("Saturday"),
	SUNDAY("Sunday");

	private final String description;

	Weekday(String description) {
	  this.description = description;
	}

    public String toString() {
	  return description;
    }
}
