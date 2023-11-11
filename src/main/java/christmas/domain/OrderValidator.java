package christmas.domain;

import java.util.Arrays;

import christmas.exception.IllegalOrderException;
import christmas.exception.IllegalOrderQuantityException;

public class OrderValidator {
	private static final String MENU_QUANTITY_DELIMITER = "-";
	private static final String FORMAT_PATTERN = "[가-힣]+-\\d";
	private static final int MAX_TOTAL_QUANTITY = 20;

	public void validatePattern(String[] input) {
		if (isNotFormatPattern(input)) {
			throw new IllegalOrderException();
		}
	}

	public void validateQuantity(String[] input) {
		if (isInvalidQuantity(input)) {
			throw new IllegalOrderException();
		}

		if (isInvalidTotalQuantity(input)) {
			throw new IllegalOrderQuantityException(MAX_TOTAL_QUANTITY);
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
			.mapToInt(this::parseQuantity)
			.anyMatch(quantity -> quantity < 1);
	}

	private boolean isInvalidTotalQuantity(String[] orders) {
		return Arrays.stream(orders)
			.mapToInt(this::parseQuantity)
			.sum() > MAX_TOTAL_QUANTITY;
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
