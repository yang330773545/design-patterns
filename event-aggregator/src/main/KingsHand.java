package main;

// 观察多个来源的事件并传递给听众（这里就是king）
public class KingsHand extends EventEmitter implements EventObserver {
	
	public KingsHand() {}
	
	public KingsHand(EventObserver obs) {
		super(obs);
	}

	@Override
	public void onEvent(Event e) {
		// TODO Auto-generated method stub
		notifyObservers(e);
	}

	@Override
	public void timePasses(Weekday day) {
		// TODO Auto-generated method stub

	}

}
