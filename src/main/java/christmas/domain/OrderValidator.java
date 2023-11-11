package christmas.domain;

import java.util.Arrays;

import christmas.exception.IllegalOrderException;

public class OrderValidator {
	private static final String MENU_QUANTITY_DELIMITER = "-";
	private static final String FORMAT_PATTERN = "[가-힣]+-\\d";

	public void validatePattern(String[] input) {
		if (isNotFormatPattern(input)) {
			throw new IllegalOrderException();
		}
	}

	public void validateQuantity(String[] input) {
		if (isInvalidQuantity(input)) {
			throw new IllegalOrderException();
		}
	}

	public void validateDuplication(String[] input) {
		if (isDuplicated(input)) {
			throw new IllegalOrderException();
		}
	}

	private boolean isNotFormatPattern(String[] orders) {
		return Arrays.stream(orders)
			.anyMatch(order -> !order.matches(FORMAT_PATTERN));
	}

	private boolean isInvalidQuantity(String[] orders) {
		return Arrays.stream(orders)
			.map(this::parseQuantity)
			.anyMatch(quantity -> quantity < 1);
	}

	private int parseQuantity(String order) {
		String quantity = order.split(MENU_QUANTITY_DELIMITER)[1];
		return Integer.parseInt(quantity);
	}

	private boolean isDuplicated(String[] orders) {
		return Arrays.stream(orders)
			.map(this::parseMenuName)
			.distinct().count() != orders.length;
	}

	private String parseMenuName(String order) {
		return order.split(MENU_QUANTITY_DELIMITER)[0];
	}
}
