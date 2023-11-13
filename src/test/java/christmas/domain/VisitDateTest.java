package christmas.domain;

import static christmas.exception.IllegalDateException.INVALID_DATE_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import christmas.dto.order.VisitDate;
import christmas.exception.IllegalDateException;

public class VisitDateTest {

	@DisplayName("유효한 날짜가 아니면 예외를 던진다.")
	@Test
	void testDateIsInValid() {
		int dayBeforeStartDate = 0;
		int dayAfterEndDate = 32;

		assertThatThrownBy(() -> new VisitDate(dayBeforeStartDate))
			.isExactlyInstanceOf(IllegalDateException.class)
			.hasMessageContaining(INVALID_DATE_MESSAGE);

		assertThatThrownBy(() -> new VisitDate(dayAfterEndDate))
			.isExactlyInstanceOf(IllegalDateException.class)
			.hasMessageContaining(INVALID_DATE_MESSAGE);
	}

	@DisplayName("유효한 날짜를 입력하면 예외를 던지지 않는다.")
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 29, 30, 31})
	void testDateIsValid(int input) {
		assertThatCode(() -> new VisitDate(input)).doesNotThrowAnyException();
	}
}
