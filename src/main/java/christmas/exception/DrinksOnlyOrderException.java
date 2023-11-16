package christmas.exception;

public class DrinksOnlyOrderException extends CommonIllegalArgumentException{
	public static final String INVALID_ORDER_MESSAGE = "유효하지 않은 주문입니다. 음료만 주문 시, 주문할 수 없습니다.";

	public DrinksOnlyOrderException() {
		super(INVALID_ORDER_MESSAGE);
	}
}
