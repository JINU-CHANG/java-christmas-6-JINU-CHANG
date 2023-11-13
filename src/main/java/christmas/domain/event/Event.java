package christmas.domain.event;

import java.time.LocalDate;

import christmas.domain.order.OrderSheet;
import christmas.domain.result.EventResult;

public abstract class Event {
	EventType eventType;
	LocalDate startDate;
	LocalDate endDate;

	public Event(EventType eventType, LocalDate startDate, LocalDate endDate) {
		this.eventType = eventType;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public boolean isDayOfWeekInDuration(LocalDate localDate) {
		return localDate.compareTo(startDate) >=0 && localDate.compareTo(endDate) <=0;
	}
	public abstract boolean isSatisfiedBy(OrderSheet orderSheet);
	public abstract EventResult getEventBenefits(OrderSheet orderSheet);
}
