package christmas.domain.event;

import christmas.domain.order.OrderForm;
import christmas.domain.result.ChristmasDDayEventResult;
import christmas.domain.result.EventResult;

public class ChristmasDDayEvent extends Event{
	private final EventType eventType = EventType.CHRISTMAS_DDAY;
	private static final int baseDiscount = 1_000;
	private static final int increment = 100;

	@Override
	public String getEventName() {
		return null;
	}

	@Override
	public boolean isMatch(OrderForm orderForm) {
		return eventType.isDayOfWeekInDuration(orderForm.getVisitDate());
	}

	@Override
	public EventResult getEventBenefits(OrderForm orderForm) {
		return new ChristmasDDayEventResult(eventType.getName(), calculateDiscount(orderForm));
	}

	private int calculateDiscount(OrderForm orderForm) {
		return baseDiscount + ((orderForm.getVisitDate().getDayOfMonth()-1) * increment);
	}
}
