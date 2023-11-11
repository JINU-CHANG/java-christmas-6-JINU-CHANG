package christmas.domain;

public class OrderInput {
	private static final String ORDERS_DELIMITER = ",";
	private final OrderValidator orderValidator;
	private final String orders;

	public OrderInput(String input, OrderValidator orderValidator) {
		validateOrders(input);
		this.orders = input;
		this.orderValidator = orderValidator;
	}

	public void validateOrders(String input) {
		String[] orders = input.split(ORDERS_DELIMITER);
		orderValidator.validatePattern(orders);
		orderValidator.validateQuantity(orders);
		orderValidator.validateDuplication(orders);
	}
}
