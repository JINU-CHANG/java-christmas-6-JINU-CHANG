package christmas.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.domain.event.Event;
import christmas.domain.event.EventType;
import christmas.domain.event.PresentEvent;
import christmas.domain.menu.Menu;
import christmas.dto.order.OrderInput;
import christmas.domain.order.OrderSheet;
import christmas.dto.order.VisitDate;
import christmas.dto.result.EventResult;

public class PresentEventTest {
	private Event presentEvent = createEvent();

	@DisplayName("샴페인을 증정하는지 확인한다.")
	@Test
	void testPresent() {
		OrderSheet moreThanRequiredPaymentOrder = new OrderSheet(new VisitDate(1), new OrderInput("해산물파스타-2,레드와인-1,초코케이크-1"));
		EventResult eventBenefit = presentEvent.getEventBenefits(moreThanRequiredPaymentOrder);

		assertThat(eventBenefit).extracting("name", "benefit", "present")
			.containsExactlyInAnyOrder("증정 이벤트", -25_000, Menu.CHAMPAGNE.getMenuName());
	}

	private Event createEvent() {
		return new PresentEvent(EventType.PRESENT,
			LocalDate.of(2023, 12, 1),
			LocalDate.of(2023, 12, 31));
	}
}
