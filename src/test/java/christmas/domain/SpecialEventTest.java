package christmas.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.domain.event.SpecialEvent;
import christmas.domain.order.OrderSheet;
import christmas.domain.order.OrderInput;
import christmas.domain.order.VisitDate;
import christmas.domain.result.EventResult;

public class SpecialEventTest {
	@DisplayName("방문 요일이 특별 요일이면 true값을 반환한다.")
	@Test
	void testVisitDateIsSpecialDay() {
		int weekendVisitDate = 3; // 특별 요일
		OrderSheet orderSheet = new OrderSheet(new VisitDate(weekendVisitDate), new OrderInput("해산물파스타-2,레드와인-1,초코케이크-1"));
		SpecialEvent specialEvent = new SpecialEvent();

		assertThat(specialEvent.isSatisfiedBy(orderSheet)).isEqualTo(true);
	}

	@DisplayName("방문 요일이 특별 요일이 아니면 false값을 반환한다.")
	@Test
	void testVisitDateIsNotSpecialDay() {
		int notWeekendVisitDate = 2;
		OrderSheet orderSheet = new OrderSheet(new VisitDate(notWeekendVisitDate), new OrderInput("해산물파스타-2,레드와인-1,초코케이크-1"));
		SpecialEvent specialEvent = new SpecialEvent();

		assertThat(specialEvent.isSatisfiedBy(orderSheet)).isEqualTo(false);
	}

	@DisplayName("특별 이벤트에 해당될 시 혜택 금액을 반환한다.")
	@Test
	void testSpecialEventBenefits() {
		int visitDate = 3; // 특별 요일
		OrderSheet orderSheet = new OrderSheet(new VisitDate(visitDate), new OrderInput("해산물파스타-2,아이스크림-2,초코케이크-1"));
		SpecialEvent specialEvent = new SpecialEvent();
		EventResult weekendEventResult = specialEvent.getEventBenefits(orderSheet);

		assertThat(weekendEventResult).extracting("name", "benefit")
			.containsExactlyInAnyOrder("특별 할인", 1000);
	}
}