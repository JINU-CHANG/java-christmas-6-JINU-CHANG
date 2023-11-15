package christmas.domain.event;

import java.time.LocalDate;

import christmas.domain.order.OrderSheet;
import christmas.dto.result.EventResult;

public abstract class Event {
	EventType eventType;
	LocalDate startDate;
	LocalDate endDate;

	public Event(EventType eventType, LocalDate startDate, LocalDate endDate) {
		this.eventType = eventType;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	protected boolean isDayOfWeekNotInDuration(LocalDate localDate) {
		return localDate.isBefore(startDate) || localDate.isAfter(endDate);
	}

	protected abstract boolean isNotSatisfiedBy(OrderSheet orderSheet);

	public abstract EventResult getEventBenefits(OrderSheet orderSheet);
}
