package christmas.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.domain.event.PresentEvent;
import christmas.domain.menu.Menu;
import christmas.domain.order.OrderForm;
import christmas.domain.order.OrderInput;
import christmas.domain.order.VisitDate;
import christmas.domain.result.EventResult;

public class EventTest {
	@DisplayName("샴페인을 증정하는지 확인한다.")
	@Test
	void testPresent() {
		OrderForm moreThanRequiredPaymentOrder = new OrderForm(new VisitDate(1), new OrderInput("해산물파스타-2,레드와인-1,초코케이크-1"));
		PresentEvent presentEvent = new PresentEvent();
		EventResult eventBenefit = presentEvent.getEventBenefits(moreThanRequiredPaymentOrder);

		assertThat(eventBenefit).extracting("name", "benefit", "present")
			.containsExactlyInAnyOrder("증정 이벤트", 25_000, Menu.CHAMPAGNE);
	}
}
