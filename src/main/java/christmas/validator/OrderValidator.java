package christmas.validator;

import java.util.Arrays;
import java.util.Map;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;
import christmas.exception.DrinksOnlyOrderException;
import christmas.exception.OrderDuplicationException;
import christmas.exception.OrderFormatPatternException;
import christmas.exception.OrderQuantityException;
import christmas.util.OrderParser;

public class OrderValidator {
	private static final String FORMAT_PATTERN = "[가-힣]+-\\d";
	private static final int MAX_TOTAL_QUANTITY = 20;

	public static void validatePattern(String[] input) {
		if (isNotFormatPattern(input)) {
			throw new OrderFormatPatternException();
		}
	}

	public static void validateQuantity(String[] input) {
		if (isInvalidQuantity(input)) {
			throw new OrderQuantityException();
		}

		if (isInvalidTotalQuantity(input)) {
			throw new OrderQuantityException(MAX_TOTAL_QUANTITY);
		}
	}

	public static void validateDuplication(String[] input) {
		if (isDuplicated(input)) {
			throw new OrderDuplicationException();
		}
	}

	public static void validateOrderOnlyDrinks(String[] input) {
		if (containsOnlyDrinks(input)) {
			throw new DrinksOnlyOrderException();
		}
	}

	private static boolean isNotFormatPattern(String[] input) {
		return Arrays.stream(input)
			.anyMatch(order -> !order.matches(FORMAT_PATTERN));
	}

	private static boolean isInvalidQuantity(String[] input) {
		return Arrays.stream(input)
			.mapToInt(OrderParser::parseQuantity)
			.anyMatch(quantity -> quantity < 1);
	}

	private static boolean isInvalidTotalQuantity(String[] input) {
		return Arrays.stream(input)
			.mapToInt(OrderParser::parseQuantity)
			.sum() > MAX_TOTAL_QUANTITY;
	}

	private static boolean isDuplicated(String[] input) {
		return Arrays.stream(input)
			.map(OrderParser::parseMenuName)
			.distinct().count() != input.length;
	}

	private static boolean containsOnlyDrinks(String[] input) {
		return Arrays.stream(input)
			.map(order -> OrderParser.parseMenuName(order))
			.allMatch(menu -> Menu.isMatchTo(menu, MenuType.DRINK));
	}
}
