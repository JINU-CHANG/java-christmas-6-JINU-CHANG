package christmas.exception;

public class OrderFormatPatternException extends CommonIllegalArgumentException{
	public static final String INVALID_ORDER_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";

	public OrderFormatPatternException() {
		super(INVALID_ORDER_MESSAGE);
	}
}
