package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.domain.event.ChristmasDDayEvent;
import christmas.domain.event.Event;
import christmas.domain.event.EventType;
import christmas.domain.order.OrderSheet;
import christmas.domain.order.OrderInput;
import christmas.domain.order.VisitDate;
import christmas.domain.result.EventResult;

public class ChristmasDDayEventTest {
	private final Event christmasDDayEvent = createEvent();

	@DisplayName("크리스마이 디데이 이벤트 할인결과를 확인한다.")
	@Test
	void testChristmasDDayEvent() {
		int visitDate = 2;
		OrderSheet orderSheet = new OrderSheet(new VisitDate(visitDate), new OrderInput("해산물파스타-2,레드와인-1,초코케이크-1"));
		EventResult eventResult = christmasDDayEvent.getEventBenefits(orderSheet);

		assertThat(eventResult).extracting("name", "benefit")
			.containsExactlyInAnyOrder("크리스마스 디데이 할인", -1100);
	}

	private Event createEvent() {
		return new ChristmasDDayEvent(EventType.CHRISTMAS_DDAY,
			LocalDate.of(2023, 12, 1),
			LocalDate.of(2023, 12, 25));
	}
}
