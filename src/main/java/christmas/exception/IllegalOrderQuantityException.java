package christmas.exception;

public class IllegalOrderQuantityException extends CommonIllegalArgumentException{
	public static final String INVALID_ORDER_QUANTITY_MESSAGE = "유효하지 않은 주문입니다. 메뉴는 한 번에 최대 %d개까지만 주문할 수 있습니다.";

	public IllegalOrderQuantityException(int totalQuantity) {
		super(String.format(INVALID_ORDER_QUANTITY_MESSAGE, totalQuantity));
	}
}
