package christmas.exception;

public class OrderQuantityException extends CommonIllegalArgumentException{
	public static final String INVALID_ORDER_QUANTITY_MESSAGE = "유효하지 않은 주문입니다. 메뉴는 한 번에 최대 %d개까지만 주문할 수 있습니다.";
	public static final String INVALID_ORDER_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
	public OrderQuantityException(int totalQuantity) {
		super(String.format(INVALID_ORDER_QUANTITY_MESSAGE, totalQuantity));
	}

	public OrderQuantityException() {
		super(INVALID_ORDER_MESSAGE);
	}
}
