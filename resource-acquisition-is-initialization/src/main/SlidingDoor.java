package main;

public class SlidingDoor implements AutoCloseable {

	public SlidingDoor() {
		System.out.println("Sliding door opens.");
	}
	
	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Sliding door closes.");
	}

}
