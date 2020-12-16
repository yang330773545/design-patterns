package main;

// 事件生产者
public class LordVarys extends EventEmitter {
	
	public LordVarys() {}
	
	public LordVarys(EventObserver obs) {
		super(obs);
	}

	@Override
	public void timePasses(Weekday day) {
		// TODO Auto-generated method stub
		if (day == Weekday.SATURDAY) {
		  notifyObservers(Event.TRAITOR_DETECTED);
		}
	}

}
