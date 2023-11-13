package christmas.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.domain.event.Event;
import christmas.domain.event.EventType;
import christmas.domain.event.WeekendEvent;
import christmas.domain.order.OrderSheet;
import christmas.domain.order.OrderInput;
import christmas.domain.order.VisitDate;
import christmas.domain.result.EventResult;

public class WeekendEventTest {
	private Event weekendEvent = createEvent();

	@DisplayName("방문 요일이 주말이면 혜택금액을 반환한다.")
	@Test
	void testVisitDateIsWeekend() {
		int weekendVisitDate = 2; //토요일
		OrderSheet orderSheet = new OrderSheet(new VisitDate(weekendVisitDate), new OrderInput("해산물파스타-2,레드와인-1,초코케이크-1"));

		assertThat(weekendEvent.getEventBenefits(orderSheet)).isNotEqualTo(null);
	}

	@DisplayName("방문 요일이 주말이 아니면 false값을 반환한다.")
	@Test
	void testVisitDateIsNotWeekend() {
		int notWeekendVisitDate = 3; //일요일
		OrderSheet orderSheet = new OrderSheet(new VisitDate(notWeekendVisitDate), new OrderInput("해산물파스타-2,레드와인-1,초코케이크-1"));

		assertThat(weekendEvent.getEventBenefits(orderSheet)).isEqualTo(null);
	}

	@DisplayName("주말 이벤트에 해당될 시 혜택 금액을 반환한다.")
	@Test
	void testWeekendEventBenefits() {
		int visitDate = 2;
		OrderSheet orderSheet = new OrderSheet(new VisitDate(visitDate), new OrderInput("해산물파스타-2,바비큐립-1,레드와인-1"));
		EventResult weekendEventResult = weekendEvent.getEventBenefits(orderSheet);

		assertThat(weekendEventResult).extracting("name", "benefit")
			.containsExactlyInAnyOrder("주말 할인", 6069);
	}

	private Event createEvent() {
		return new WeekendEvent(EventType.WEEKEND,
			LocalDate.of(2023, 12, 1),
			LocalDate.of(2023, 12, 31));
	}
}
