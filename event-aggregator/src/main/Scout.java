package main;

// 生产者 侦察兵侦察敌情
public class Scout extends EventEmitter {
	public Scout() {}

	public Scout(EventObserver obs) {
		super(obs);
	}

	@Override
	public void timePasses(Weekday day) {
		// TODO Auto-generated method stub
		if (day == Weekday.TUESDAY) {
	      notifyObservers(Event.WARSHIPS_APPROACHING);
	    }
	}

}
