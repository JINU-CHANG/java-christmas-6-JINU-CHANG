package christmas.validator;

import java.util.Arrays;
import java.util.Map;

import christmas.domain.menu.Menu;
import christmas.domain.menu.Type;
import christmas.exception.DrinksOnlyOrderException;
import christmas.exception.IllegalOrderException;
import christmas.exception.IllegalOrderQuantityException;
import christmas.util.OrderParser;

public class OrderValidator {
	private static final String FORMAT_PATTERN = "[가-힣]+-\\d";
	private static final int MAX_TOTAL_QUANTITY = 20;

	public static void validatePattern(String[] input) {
		if (isNotFormatPattern(input)) {
			throw new IllegalOrderException();
		}
	}

	public static void validateQuantity(String[] input) {
		if (isInvalidQuantity(input)) {
			throw new IllegalOrderException();
		}

		if (isInvalidTotalQuantity(input)) {
			throw new IllegalOrderQuantityException(MAX_TOTAL_QUANTITY);
		}
	}

	public static void validateDuplication(String[] input) {
		if (isDuplicated(input)) {
			throw new IllegalOrderException();
		}
	}

	public static void validateOrderOnlyDrinks(Map<Menu, Integer> orders) {
		if (containsOnlyDrinks(orders)) {
			throw new DrinksOnlyOrderException();
		}
	}

	private static boolean isNotFormatPattern(String[] orders) {
		return Arrays.stream(orders)
			.anyMatch(order -> !order.matches(FORMAT_PATTERN));
	}

	private static boolean isInvalidQuantity(String[] orders) {
		return Arrays.stream(orders)
			.mapToInt(OrderParser::parseQuantity)
			.anyMatch(quantity -> quantity < 1);
	}

	private static boolean isInvalidTotalQuantity(String[] orders) {
		return Arrays.stream(orders)
			.mapToInt(OrderParser::parseQuantity)
			.sum() > MAX_TOTAL_QUANTITY;
	}

	private static boolean isDuplicated(String[] orders) {
		return Arrays.stream(orders)
			.map(OrderParser::parseMenuName)
			.distinct().count() != orders.length;
	}

	private static boolean containsOnlyDrinks(Map<Menu, Integer> orders) {
		return orders.keySet().stream()
			.allMatch(menu -> menu.getType().equals(Type.DRINK));
	}
}
