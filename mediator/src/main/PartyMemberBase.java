package main;

// party会员抽象类
public abstract class PartyMemberBase implements PartyMember {

	protected Party party;
	
	@Override
	public void joinedParty(Party party) {
		System.out.println(this + "json the party");
		this.party = party;
	}

	@Override
	public void partyAction(Action action) {
		System.out.println(this + action.getDescription());
	}

	@Override
	public void act(Action action) {
		if(party != null) {
			System.out.println(this + action.toString());
			party.act(this, action);
		}
	}
	
	@Override
	public abstract String toString();

}
