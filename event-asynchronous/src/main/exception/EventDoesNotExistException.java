package main.exception;

/**
 * 
 * 不存在事件时的自定义异常
 *
 */
public class EventDoesNotExistException extends Exception {

	private static final long serialVersionUID = -3398463738273811509L;

	public EventDoesNotExistException(String message) {
		super(message);
	}
}
