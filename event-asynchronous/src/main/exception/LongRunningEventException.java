package main.exception;

/**
 * 
 * 运行时间过长异常
 *
 */
public class LongRunningEventException extends Exception {
	private static final long serialVersionUID = -483423544320148809L;

	public LongRunningEventException(String message) {
	    super(message);
	}
}
