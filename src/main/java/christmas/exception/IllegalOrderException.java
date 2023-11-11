package christmas.exception;

public class IllegalOrderException extends CommonIllegalArgumentException{
	public static final String INVALID_ORDER_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
	public IllegalOrderException() {
		super(INVALID_ORDER_MESSAGE);
	}
}
