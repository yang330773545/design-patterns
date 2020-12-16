package main;

// 产生事件的生产者
public class LordBaelish extends EventEmitter {

    public LordBaelish() {
    }

    public LordBaelish(EventObserver obs) {
      super(obs);
    }
    
	@Override
	public void timePasses(Weekday day) {
		// TODO Auto-generated method stub
		if (day == Weekday.FRIDAY) {
			notifyObservers(Event.STARK_SIGHTED);
		}
	}

}
