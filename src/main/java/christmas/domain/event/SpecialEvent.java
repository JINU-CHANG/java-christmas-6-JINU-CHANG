package christmas.domain.event;

import java.time.DayOfWeek;
import java.time.LocalDate;

import christmas.domain.order.OrderForm;
import christmas.domain.result.EventResult;
import christmas.domain.result.SpecialEventResult;

public class SpecialEvent extends Event{
	private final EventType eventType = EventType.SPECIAL;
	private static final int discount = 1_000;
	@Override
	public boolean isMatch(OrderForm orderForm) {
		return eventType.isDayOfWeekInDuration(orderForm.getVisitDate()) && isSpecialDay(orderForm.getVisitDate());
	}

	@Override
	public EventResult getEventBenefits(OrderForm orderForm) {
		return new SpecialEventResult(eventType.getName(), discount);
	}

	private boolean isSpecialDay(LocalDate localDate) {
		return (localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) || (localDate.getDayOfMonth()==25);
	}

}
