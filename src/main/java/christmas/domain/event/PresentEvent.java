package christmas.domain.event;

import christmas.domain.menu.Menu;
import christmas.domain.order.OrderForm;
import christmas.domain.result.EventResult;
import christmas.domain.result.PresentEventResult;

public class PresentEvent extends Event{
	private final EventType eventType = EventType.PRESENT;
	private final Menu present = Menu.CHAMPAGNE;
	private static final int presentQuantity = 1;
	private static final int requiredPayment = 120_000;

	@Override
	public String getEventName() {
		return eventType.getName();
	}

	@Override
	public boolean isMatch(OrderForm orderForm) {
		return eventType.isDayOfWeekInDuration(orderForm.getVisitDate()) && isMoreThanStandardPayment(orderForm.getTotalPayment());
	}

	@Override
	public EventResult getEventBenefits(OrderForm orderForm) {
		return new PresentEventResult(eventType.getName(), present.getPrice(), Menu.CHAMPAGNE, presentQuantity);
	}

	private boolean isMoreThanStandardPayment(int totalPayment) {
		return totalPayment >= requiredPayment;
	}
}
