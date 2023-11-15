package christmas.exception;

public class CommonIllegalArgumentException extends IllegalArgumentException {
	public static final String EXCEPTION_PREFIX = "[ERROR] %s";

	public CommonIllegalArgumentException(String message) {
		super(String.format(EXCEPTION_PREFIX, message));
	}
}
