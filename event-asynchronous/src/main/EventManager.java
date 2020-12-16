package main;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import main.exception.EventDoesNotExistException;
import main.exception.InvalidOperationException;
import main.exception.LongRunningEventException;
import main.exception.MaxNumOfEventsAllowedException;

/**
 * 
 * 处理并维护线程池
 *
 */
public class EventManager implements ThreadCompleteListener {
	public static final int MAX_RUNNING_EVENTS = 1000;
	// 运行事件 这里懒得搞那么多
	public static final int MIN_ID = 1;
	public static final int MAX_ID = MAX_RUNNING_EVENTS;
	public static final int MAX_EVENT_TIME = 1800; // in seconds / 30 minutes.
	private int currentlyRunningSyncEvent = -1;
	private final Random rand;
	private final Map<Integer, Event> eventPool;
	
	private static final String DOES_NOT_EXIST = " does not exist.";
	
	public EventManager() {
		rand = new Random(1);
	    eventPool = new ConcurrentHashMap<Integer, Event>(MAX_RUNNING_EVENTS);
	}

	// 事件完成后的方法回调，将该事件从池中移除
	@Override
	public void completedEventHandler(int eventId) {
		// TODO Auto-generated method stub
		eventPool.get(eventId).status();
		if (eventPool.get(eventId).isSynchronous()) {
			currentlyRunningSyncEvent = -1;
	    }
	    eventPool.remove(eventId);
	}
	
	// 创建一个同步事件 参数为事件运行时间
	public int create(int eventTime) throws MaxNumOfEventsAllowedException, InvalidOperationException, LongRunningEventException {
		if (currentlyRunningSyncEvent != -1) {
			throw new InvalidOperationException("Event [" + currentlyRunningSyncEvent + "] is still" 
					+ " running. Please wait until it finishes and try again.");
		}
		int eventId = createEvent(eventTime, true);
	    currentlyRunningSyncEvent = eventId;

	    return eventId;
	}

	// 创建一个异步事件
	public int createAsync(int eventTime) throws MaxNumOfEventsAllowedException, LongRunningEventException {
		return createEvent(eventTime, false);
	}
	
	private int createEvent(int eventTime, boolean isSynchronous) throws MaxNumOfEventsAllowedException, LongRunningEventException {
		if (eventPool.size() == MAX_RUNNING_EVENTS) {
			throw new MaxNumOfEventsAllowedException("Too many events are running at the moment." + " Please try again later.");
		}

	    if (eventTime >= MAX_EVENT_TIME) {
	    	throw new LongRunningEventException("Maximum event time allowed is " + MAX_EVENT_TIME + " seconds. Please try again.");
	    }

	    int newEventId = generateId();

	    Event newEvent = new Event(newEventId, eventTime, isSynchronous);
	    newEvent.addListener(this);
	    eventPool.put(newEventId, newEvent);

	    return newEventId;
	}
	
	// 生成随机数
	private int generateId() {
	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((MAX_ID - MIN_ID) + 1) + MIN_ID;
	    while (eventPool.containsKey(randomNum)) {
	        randomNum = rand.nextInt((MAX_ID - MIN_ID) + 1) + MIN_ID;
	    }
	
	    return randomNum;
    }
	
	// 启动事件
	public void start(int eventId) throws EventDoesNotExistException {
	    if (!eventPool.containsKey(eventId)) {
	        throw new EventDoesNotExistException(eventId + DOES_NOT_EXIST);
	    }

	    eventPool.get(eventId).start();
	}

	// 停止事件
	public void cancel(int eventId) throws EventDoesNotExistException {
		if (!eventPool.containsKey(eventId)) {
	        throw new EventDoesNotExistException(eventId + DOES_NOT_EXIST);
	    }

	    if (eventId == currentlyRunningSyncEvent) {
	        currentlyRunningSyncEvent = -1;
	    }

	    eventPool.get(eventId).stop();
	    eventPool.remove(eventId);
	  }

	// 获取事件状态
	public void status(int eventId) throws EventDoesNotExistException {
	    if (!eventPool.containsKey(eventId)) {
	        throw new EventDoesNotExistException(eventId + DOES_NOT_EXIST);
	    }

	    eventPool.get(eventId).status();
	}

	// 获取所有正在运行的事件状态
	@SuppressWarnings("rawtypes")
	public void statusOfAllEvents() {
	    eventPool.entrySet().forEach(entry -> ((Event) ((Map.Entry) entry).getValue()).status());
	}
	
    public Map<Integer, Event> getEventPool() {
    	return eventPool;
    }

    // 获取正在运行的同步线程的数量
	public int numOfCurrentlyRunningSyncEvent() {
		return currentlyRunningSyncEvent;
	}
}
