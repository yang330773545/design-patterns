package main;

import main.exception.EventDoesNotExistException;
import main.exception.InvalidOperationException;
import main.exception.LongRunningEventException;
import main.exception.MaxNumOfEventsAllowedException;

public class App {
	
	public static void main(String[] args) {
		App app = new App();
		app.quickRun();
	}
	
	public void quickRun() {
		EventManager eventManager = new EventManager();

		try {
			// Create an Asynchronous event.
	        int asyncEventId = eventManager.createAsync(60);
	        System.out.println("Async Event [" + asyncEventId  +"] has been created.");
	        eventManager.start(asyncEventId);
	        System.out.println("Async Event [" + asyncEventId  +"] has been started.");

	        // Create a Synchronous event.
	        int syncEventId = eventManager.create(60);
	        System.out.println("Sync Event [" + asyncEventId  +"] has been created.");
	        eventManager.start(syncEventId);
	        System.out.println("Sync Event [" + asyncEventId  +"] has been started.");

	        eventManager.status(asyncEventId);
	        eventManager.status(syncEventId);

	        eventManager.cancel(asyncEventId);
	        System.out.println("Async Event [" + asyncEventId  +"] has been stopped.");
	        eventManager.cancel(syncEventId);
	        System.out.println("Sync Event [" + asyncEventId  +"] has been stopped.");

	    } catch (MaxNumOfEventsAllowedException | LongRunningEventException | EventDoesNotExistException | InvalidOperationException e) {
	        System.out.println(e.getMessage());
	    }
		}
}
