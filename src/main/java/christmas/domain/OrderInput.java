package christmas.domain;

import christmas.util.OrderParser;

public class OrderInput {
	private final OrderValidator orderValidator;
	private final String orders;

	public OrderInput(String input, OrderValidator orderValidator) {
		this.orderValidator = orderValidator;
		validateOrders(input);
		this.orders = input;
	}

	public String[] getOrders() {
		return OrderParser.parseOrders(orders);
	}

	private void validateOrders(String input) {
		String[] orders = OrderParser.parseOrders(input);
		orderValidator.validatePattern(orders);
		orderValidator.validateQuantity(orders);
		orderValidator.validateDuplication(orders);
	}
}
