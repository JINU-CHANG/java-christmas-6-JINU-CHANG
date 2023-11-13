package christmas.domain.event;

import java.time.DayOfWeek;
import java.time.LocalDate;

import christmas.domain.order.OrderSheet;
import christmas.dto.result.EventResult;
import christmas.dto.result.SpecialEventResult;

public class SpecialEvent extends Event{
	private static final int discount = -1_000;

	public SpecialEvent(EventType eventType, LocalDate startDate, LocalDate endDate) {
		super(eventType, startDate, endDate);
	}

	@Override
	protected boolean isSatisfiedBy(OrderSheet orderSheet) {
		return isDayOfWeekInDuration(orderSheet.getVisitDate()) && isSpecialDay(orderSheet.getVisitDate());
	}

	@Override
	public EventResult getEventBenefits(OrderSheet orderSheet) {
		if (isSatisfiedBy(orderSheet)) {
			return new SpecialEventResult(eventType.getName(), discount);
		}
		return null;
	}

	private boolean isSpecialDay(LocalDate localDate) {
		return (localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) || (localDate.getDayOfMonth()==25);
	}

}
