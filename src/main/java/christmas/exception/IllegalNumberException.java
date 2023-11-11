package christmas.exception;

public class IllegalNumberException extends CommonIllegalArgumentException {
	public static final String INVALID_NUMBER_MESSAGE = "유효하지 않은 숫자입니다. 다시 입력해주세요.";

	public IllegalNumberException() {
		super(INVALID_NUMBER_MESSAGE);
	}
}
