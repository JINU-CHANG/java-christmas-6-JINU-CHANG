package christmas.domain;

import java.util.Arrays;

import christmas.exception.IllegalOrderException;

public class OrderConvertor {
	private static final String DELIMITER = ",";
	private static final String FORMAT_PATTERN = "[가-힣]+-\\d";

	public void convertToOrders(String input) {
		String[] strings = input.split(DELIMITER);
		validateOrderFormat(strings);
	}

	private void validateOrderFormat(String[] input) {
		if (checkInvalidOrderFormat(input)) {
			throw new IllegalOrderException();
		}
	}

	private boolean checkInvalidOrderFormat(String[] input) {
		return Arrays.stream(input)
			.anyMatch(this::isNotOrderFormat);
	}

	private boolean isNotOrderFormat(String input) {
		return !input.matches(FORMAT_PATTERN);
	}
}
