package main;

/**
 * 
 * 每一个事件都作为一个单独的线程
 *
 */
public class Event implements IEvent, Runnable {
	
	private final int eventId;
	private final int eventTime;
	private final boolean isSynchronous;
	private Thread thread;
	private boolean isComplete = false;
	private ThreadCompleteListener eventListener;
	
	public Event(final int eventId, final int eventTime, final boolean isSynchronous) {
		this.eventId = eventId;
		this.eventTime = eventTime;
		this.isSynchronous = isSynchronous;
	}

	public boolean isSynchronous() {
		return isSynchronous;
	}
	
	@Override
	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
		if(thread == null) {
			return;
		}
		// 中断该线程
		thread.interrupt();
	}

	@Override
	public void status() {
		if (!isComplete) {
			System.out.println(eventId + " is not down");
		} else {
			System.out.println(eventId + " is down");
		}
	}

	@Override
	public void run() {
		long currentTime = System.currentTimeMillis();
		long endTime = currentTime + (eventTime * 1000);
		while (System.currentTimeMillis() < endTime) {
			try {
				thread.sleep(1000);
			} catch(InterruptedException e) {
				return;
			}
		}
		isComplete = true;
		completed();
	}

	private void completed() {
		if (eventListener != null) {
			eventListener.completedEventHandler(eventId);
		}
	}
	
	public final void addListener(final ThreadCompleteListener listener) {
		this.eventListener = listener;
	}

	public final void removeListener(final ThreadCompleteListener listener) {
		this.eventListener = null;
	}
}
