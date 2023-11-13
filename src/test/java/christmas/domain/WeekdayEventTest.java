package christmas.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.domain.event.WeekdayEvent;
import christmas.domain.order.OrderForm;
import christmas.domain.order.OrderInput;
import christmas.domain.order.VisitDate;
import christmas.domain.result.EventResult;

public class WeekdayEventTest {
	@DisplayName("방문 요일이 평일이면 true값을 반환한다.")
	@Test
	void testVisitDateIsWeekday() {
		int weekendVisitDate = 3; // 평일
		OrderForm orderForm = new OrderForm(new VisitDate(weekendVisitDate), new OrderInput("해산물파스타-2,레드와인-1,초코케이크-1"));
		WeekdayEvent weekdayEvent = new WeekdayEvent();

		assertThat(weekdayEvent.isMatch(orderForm)).isEqualTo(true);
	}

	@DisplayName("방문 요일이 평일이 아니면 false값을 반환한다.")
	@Test
	void testVisitDateIsNotWeekend() {
		int notWeekendVisitDate = 2; // 주말
		OrderForm orderForm = new OrderForm(new VisitDate(notWeekendVisitDate), new OrderInput("해산물파스타-2,레드와인-1,초코케이크-1"));
		WeekdayEvent weekdayEvent = new WeekdayEvent();

		assertThat(weekdayEvent.isMatch(orderForm)).isEqualTo(false);
	}

	@DisplayName("평일 이벤트에 해당될 시 혜택 금액을 반환한다.")
	@Test
	void testWeekendEventBenefits() {
		int visitDate = 3; // 평일
		OrderForm orderForm = new OrderForm(new VisitDate(visitDate), new OrderInput("해산물파스타-2,아이스크림-2,초코케이크-1"));
		WeekdayEvent weekdayEvent = new WeekdayEvent();
		EventResult weekendEventResult = weekdayEvent.getEventBenefits(orderForm);

		assertThat(weekendEventResult).extracting("name", "benefit")
			.containsExactlyInAnyOrder("평일 할인", 6069);
	}
}
