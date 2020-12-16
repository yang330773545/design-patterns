package main;

// party互动
public interface PartyMember {

	void joinedParty(Party party);
	
	void partyAction(Action action);
	
	void act(Action action);
}
