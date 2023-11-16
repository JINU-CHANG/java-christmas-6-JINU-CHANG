package christmas.exception;

public class CommonRuntimeException extends RuntimeException{
	public static final String EXCEPTION_PREFIX = "[ERROR] %s";

	public CommonRuntimeException(String message) {
		super(String.format(EXCEPTION_PREFIX, message));
	}
}
