package christmas.domain.event;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.mockito.internal.matchers.Or;

import christmas.domain.order.OrderSheet;
import christmas.domain.result.EventResult;

public class EventPlanner {
	private final Set<Event> events;
	private static final int eventCondition = 10_000;

	public EventPlanner(Set<Event> events) {
		this.events = new HashSet<>(events);
	}

	public Set<EventResult> calculate(OrderSheet orderSheet) {
		if (isSatisfiedBy(orderSheet)) {
			return events.stream()
				.filter(event -> event.isSatisfiedBy(orderSheet))
				.map(event-> event.getEventBenefits(orderSheet))
				.collect(Collectors.toSet());
		}
		return null;
	}

	private boolean isSatisfiedBy(OrderSheet orderSheet) {
		return orderSheet.getTotalPayment() >= eventCondition;
	}
}
