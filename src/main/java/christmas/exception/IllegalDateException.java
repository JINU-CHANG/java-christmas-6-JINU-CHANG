package christmas.exception;

public class IllegalDateException extends CommonIllegalArgumentException {
	public static final String INVALID_DATE_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

	public IllegalDateException() {
		super(INVALID_DATE_MESSAGE);
	}
}
