package christmas.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.domain.event.WeekendEvent;
import christmas.domain.order.OrderForm;
import christmas.domain.order.OrderInput;
import christmas.domain.order.VisitDate;
import christmas.domain.result.EventResult;

public class WeekendEventTest {
	@DisplayName("방문 요일이 주말이면 true값을 반환한다.")
	@Test
	void testVisitDateIsWeekend() {
		int weekendVisitDate = 2; //토요일
		OrderForm orderForm = new OrderForm(new VisitDate(weekendVisitDate), new OrderInput("해산물파스타-2,레드와인-1,초코케이크-1"));
		WeekendEvent weekendEvent = new WeekendEvent();

		assertThat(weekendEvent.isMatch(orderForm)).isEqualTo(true);
	}

	@DisplayName("방문 요일이 주말이 아니면 false값을 반환한다.")
	@Test
	void testVisitDateIsNotWeekend() {
		int notWeekendVisitDate = 3; //일요일
		OrderForm orderForm = new OrderForm(new VisitDate(notWeekendVisitDate), new OrderInput("해산물파스타-2,레드와인-1,초코케이크-1"));
		WeekendEvent weekendEvent = new WeekendEvent();

		assertThat(weekendEvent.isMatch(orderForm)).isEqualTo(false);
	}

	@DisplayName("주말 이벤트에 해당될 시 혜택 금액을 반환한다.")
	@Test
	void testWeekendEventBenefits() {
		int visitDate = 2;
		OrderForm orderForm = new OrderForm(new VisitDate(visitDate), new OrderInput("해산물파스타-2,바비큐립-1,레드와인-1"));
		WeekendEvent weekendEvent = new WeekendEvent();
		EventResult weekendEventResult = weekendEvent.getEventBenefits(orderForm);

		assertThat(weekendEventResult).extracting("name", "benefit")
			.containsExactlyInAnyOrder("주말 할인", 6069);
	}
}