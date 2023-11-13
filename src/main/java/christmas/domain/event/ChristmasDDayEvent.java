package christmas.domain.event;

import christmas.domain.order.OrderSheet;
import christmas.domain.result.ChristmasDDayEventResult;
import christmas.domain.result.EventResult;

public class ChristmasDDayEvent extends Event{
	private final EventType eventType = EventType.CHRISTMAS_DDAY;
	private static final int baseDiscount = 1_000;
	private static final int increment = 100;

	@Override
	public boolean isSatisfiedBy(OrderSheet orderSheet) {
		return eventType.isDayOfWeekInDuration(orderSheet.getVisitDate());
	}

	@Override
	public EventResult getEventBenefits(OrderSheet orderSheet) {
		return new ChristmasDDayEventResult(eventType.getName(), calculateDiscount(orderSheet));
	}

	private int calculateDiscount(OrderSheet orderSheet) {
		return baseDiscount + ((orderSheet.getVisitDate().getDayOfMonth()-1) * increment);
	}
}
