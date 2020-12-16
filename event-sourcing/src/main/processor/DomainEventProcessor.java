package main.processor;

import main.event.DomainEvent;

// 事件处理器
public class DomainEventProcessor {

	private final JsonFileJournal processorJournal = new JsonFileJournal();
	
	public void process(DomainEvent domainEvent) {
	    domainEvent.process();
	    processorJournal.write(domainEvent);
	}
	
	public void reset() {
	    processorJournal.reset();
	}

	public void recover() {
	    DomainEvent domainEvent;
	    while ((domainEvent = processorJournal.readNext()) != null) {
	        domainEvent.process();
	    }
	}
}
