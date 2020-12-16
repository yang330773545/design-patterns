package main;

import java.util.ArrayList;
import java.util.List;

public class PartyImpl implements Party {
	
	private final List<PartyMember> members;

	public PartyImpl() {
		members = new ArrayList<PartyMember>();
	}
	
	@Override
	public void addMember(PartyMember member) {
		members.add(member);
	}

	@Override
	public void act(PartyMember actor, Action action) {
		for(PartyMember member : members) {
			if(!member.equals(actor)) {
				member.partyAction(action);
			}
		}
	}

}
