package main;

public class App {

	public static void main(String[] args) {
		Party party = new PartyImpl();
		
		Hobbit hobbit = new Hobbit();
		Hunter hunter = new Hunter();
		
		party.addMember(hobbit);
		party.addMember(hunter);
		
		hobbit.act(Action.ENEMY);
		hunter.act(Action.GOLD);
	}
}
