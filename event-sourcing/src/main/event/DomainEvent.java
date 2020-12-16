package main.event;

import java.io.Serializable;

// 所有的事件都必须继承此类
public abstract class DomainEvent implements Serializable {

	private final long sequenceId;
	private final long createdTime;
	private final String eventClassName;
	private boolean realTime = true;
	
	public DomainEvent(long sequenceId, long createdTime, String eventClassName) {
	    this.sequenceId = sequenceId;
	    this.createdTime = createdTime;
	    this.eventClassName = eventClassName;
	}

	public long getSequenceId() {
		return sequenceId;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public String getEventClassName() {
		return eventClassName;
	}

	public boolean isRealTime() {
		return realTime;
	}
	
	public void setRealTime(boolean realTime) {
		this.realTime = realTime;
	}
	
	public abstract void process();
}
