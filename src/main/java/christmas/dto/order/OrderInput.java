package christmas.dto.order;

import christmas.util.OrderParser;
import christmas.validator.OrderValidator;

public class OrderInput {
	private final String orders;

	public OrderInput(String input) {
		validateOrders(input);
		this.orders = input;
	}

	public String[] getOrders() {
		return OrderParser.parseOrders(orders);
	}

	private void validateOrders(String input) {
		String[] orders = OrderParser.parseOrders(input);
		OrderValidator.validatePattern(orders);
		OrderValidator.validateQuantity(orders);
		OrderValidator.validateDuplication(orders);
	}
}
