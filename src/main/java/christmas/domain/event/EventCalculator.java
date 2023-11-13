package christmas.domain.event;

import java.util.Set;
import java.util.stream.Collectors;

import christmas.domain.order.OrderForm;
import christmas.domain.result.EventResult;

public class EventCalculator {
	private final Set<Event> events;

	public EventCalculator(Set<Event> events) {
		this.events = events;
	}

	public Set<EventResult> calculate(OrderForm orderForm) {
		return events.stream()
			.filter(event -> event.isMatch(orderForm))
			.map(event-> event.getEventBenefits(orderForm))
			.collect(Collectors.toSet());
	}
}
