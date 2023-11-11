package christmas.domain;

import static christmas.exception.IllegalOrderException.INVALID_ORDER_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import christmas.exception.IllegalOrderException;

public class OrderValidatorTest {
	private OrderValidator orderValidator = new OrderValidator();

	@DisplayName("주문 형식이 예시와 다를 경우 예외를 던진다.")
	@ParameterizedTest
	@ValueSource(strings = {"해산물파스타--2,레드와인-1,초코케이크-1", "2-해산물파스타,레드와인-1,초코케이크-1", "해산물파스타--2,redwine-1,초코케이크-1"})
	void whenOrderFormatIsInvalidThrowException(String input) {
		assertThatThrownBy(() -> orderValidator.validateOrderFormat(input))
			.isExactlyInstanceOf(IllegalOrderException.class)
			.hasMessageContaining(INVALID_ORDER_MESSAGE);
	}

	@DisplayName("주문 수량이 1이상이 아닐 경우 예외를 던진다.")
	@Test
	void whenOrderQuantityIsInvalidThrowException() {
		String invalidInput = "해산물파스타-0,레드와인-1,초코케이크-1";
		assertThatThrownBy(() -> orderValidator.validateOrderFormat(invalidInput))
			.isExactlyInstanceOf(IllegalOrderException.class)
			.hasMessageContaining(INVALID_ORDER_MESSAGE);
	}
}
