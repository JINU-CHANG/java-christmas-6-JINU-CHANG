package christmas.domain.event;

import java.time.LocalDate;
import java.util.Optional;

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

	protected boolean isDayOfWeekInDuration(LocalDate localDate) {
		return localDate.compareTo(startDate) >=0 && localDate.compareTo(endDate) <=0;
	}
	protected abstract boolean isSatisfiedBy(OrderSheet orderSheet);
	public abstract EventResult getEventBenefits(OrderSheet orderSheet);
}
