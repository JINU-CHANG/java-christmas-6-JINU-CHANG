package christmas.domain;

import static christmas.exception.IllegalOrderException.INVALID_ORDER_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import christmas.exception.IllegalOrderException;
import christmas.exception.IllegalOrderQuantityException;
import christmas.util.OrderValidator;

public class OrderValidatorTest {
	private OrderValidator orderValidator = new OrderValidator();

	@DisplayName("주문 형식이 예시와 다를 경우 예외를 던진다.")
	@ParameterizedTest
	@ValueSource(strings = {"해산물파스타--2,레드와인-1,초코케이크-1", "2-해산물파스타,레드와인-1,초코케이크-1", "해산물파스타--2,redwine-1,초코케이크-1"})
	void whenOrderFormatIsInvalidThrowException(String input) {
		String[] invalidOrders = input.split(",");
		assertThatThrownBy(() -> orderValidator.validatePattern(invalidOrders))
			.isExactlyInstanceOf(IllegalOrderException.class)
			.hasMessageContaining(INVALID_ORDER_MESSAGE);
	}

	@DisplayName("주문 수량이 1이상이 아닐 경우 예외를 던진다.")
	@Test
	void whenOrderQuantityIsInvalidThrowException() {
		String[] invalidOrders = {"해산물파스타-0", "레드와인-1", "초코케이크-1"};
		assertThatThrownBy(() -> orderValidator.validateQuantity(invalidOrders))
			.isExactlyInstanceOf(IllegalOrderException.class)
			.hasMessageContaining(INVALID_ORDER_MESSAGE);
	}

	@DisplayName("중복 메뉴가 존재하는 경우 예외를 던진다.")
	@Test
	void whenOrderIsDuplicatedThrowException() {
		String[] invalidOrders = {"해산물파스타-1", "해산물파스타-1", "레드와인-1"};
		assertThatThrownBy(() -> orderValidator.validateDuplication(invalidOrders))
			.isExactlyInstanceOf(IllegalOrderException.class)
			.hasMessageContaining(INVALID_ORDER_MESSAGE);
	}

	@DisplayName("총 주문 수량이 최대 주문수량보다 많으면 예외를 던진다.")
	@Test
	void whenTotalOrderQuantityIsBiggerThanMaxThrowException() {
		String[] invalidOrders = {"해산물파스타-10", "레드와인-10", "초코케이크-1"};
		assertThatThrownBy(() -> orderValidator.validateQuantity(invalidOrders))
			.isExactlyInstanceOf(IllegalOrderQuantityException.class);
	}
}
