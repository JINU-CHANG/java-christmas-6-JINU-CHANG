package christmas.domain;

import static christmas.exception.IllegalOrderException.INVALID_ORDER_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import christmas.exception.IllegalOrderException;

public class OrderConvertorTest {
	@DisplayName("주문 형식이 예시와 다를 경우 예외를 던진다.")
	@ParameterizedTest
	@ValueSource(strings = {"해산물파스타--2,레드와인-1,초코케이크-1", "2-해산물파스타,레드와인-1,초코케이크-1", "해산물파스타--2,redwine-1,초코케이크-1"})
	void testOrderFormatIsInvalid(String input) {
		OrderConvertor convertor = new OrderConvertor();

		assertThatThrownBy(() -> convertor.convertToOrders(input))
			.isExactlyInstanceOf(IllegalOrderException.class)
			.hasMessageContaining(INVALID_ORDER_MESSAGE);
	}
}
