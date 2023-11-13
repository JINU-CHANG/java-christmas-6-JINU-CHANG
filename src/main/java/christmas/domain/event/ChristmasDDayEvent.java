package christmas.domain.event;

import java.time.LocalDate;

import christmas.domain.order.OrderSheet;
import christmas.domain.result.ChristmasDDayEventResult;
import christmas.domain.result.EventResult;

public class ChristmasDDayEvent extends Event{
	private static final int baseDiscount = -1_000;
	private static final int increment = -100;

	public ChristmasDDayEvent(EventType eventType, LocalDate startDate, LocalDate endDate) {
		super(eventType, startDate, endDate);
	}

	@Override
	public boolean isSatisfiedBy(OrderSheet orderSheet) {
		return isDayOfWeekInDuration(orderSheet.getVisitDate());
	}

	@Override
	public EventResult getEventBenefits(OrderSheet orderSheet) {
		if (isSatisfiedBy(orderSheet)) {
			return new ChristmasDDayEventResult(eventType.getName(), calculateDiscount(orderSheet));
		}
		return null;
	}

	private int calculateDiscount(OrderSheet orderSheet) {
		return (baseDiscount + ((orderSheet.getVisitDate().getDayOfMonth()-1) * increment));
	}
}
