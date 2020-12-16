package main;

import java.util.LinkedList;
import java.util.List;

// 观察事件生产者的基类
public abstract class EventEmitter {
	private final List<EventObserver> observers;
	
	public EventEmitter() {
	  observers = new LinkedList<>();
	}

	public EventEmitter(EventObserver obs) {
	  this();
	  registerObserver(obs);
	}
	
	public final void registerObserver(EventObserver obs) {
	  observers.add(obs);
	}
	
	protected void notifyObservers(Event e) {
	  observers.forEach(obs -> obs.onEvent(e));
	}

	public abstract void timePasses(Weekday day);

}
