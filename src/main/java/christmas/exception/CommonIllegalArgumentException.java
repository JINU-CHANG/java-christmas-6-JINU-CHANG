package christmas.exception;

public class CommonIllegalArgumentException extends IllegalArgumentException {
	public static final String ERROR_PREFIX = "[ERROR] %s";

	public CommonIllegalArgumentException(String message) {
		super(String.format(ERROR_PREFIX, message));
	}
}
