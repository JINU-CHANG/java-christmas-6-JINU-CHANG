package christmas.exception;

public class CommonRuntimeException extends RuntimeException{
	public static final String ERROR_PREFIX = "[ERROR] %s";

	public CommonRuntimeException(String message) {
		super(String.format(ERROR_PREFIX, message));
	}
}
