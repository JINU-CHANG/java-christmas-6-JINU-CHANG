package christmas.domain.event;

import java.time.DayOfWeek;
import java.time.LocalDate;

import christmas.domain.order.OrderSheet;
import christmas.dto.result.EventResult;
import christmas.dto.result.SpecialEventResult;

public class SpecialEvent extends Event implements Discountable{
	private static final int discount = 1_000;

	public SpecialEvent(EventType eventType, LocalDate startDate, LocalDate endDate) {
		super(eventType, startDate, endDate);
	}

	@Override
	protected boolean isNotSatisfiedBy(OrderSheet orderSheet) {
		return isDayOfWeekNotInDuration(orderSheet.getVisitDate()) || isNotSpecialDay(orderSheet.getVisitDate());
	}

	@Override
	public EventResult getEventBenefits(OrderSheet orderSheet) {
		if (isNotSatisfiedBy(orderSheet)) {
			return null;
		}
		return new SpecialEventResult(eventType.getName(), discount);
	}

	private boolean isNotSpecialDay(LocalDate localDate) {
		return !(localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY) || (localDate.getDayOfMonth()==25));
	}

}
