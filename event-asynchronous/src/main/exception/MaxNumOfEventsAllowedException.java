package main.exception;

/**
 * 
 * 超过允许数量的最大数 异常
 *
 */
public class MaxNumOfEventsAllowedException extends Exception {
	private static final long serialVersionUID = -8430876973516292695L;

	public MaxNumOfEventsAllowedException(String message) {
	    super(message);
	}
}
