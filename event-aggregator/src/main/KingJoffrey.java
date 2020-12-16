package main;

// 实现观察
public class KingJoffrey implements EventObserver{

	@Override
	public void onEvent(Event e) {
		// TODO Auto-generated method stub
		System.out.println("Received event from the King's Hand: " + e.toString());
	}

}
