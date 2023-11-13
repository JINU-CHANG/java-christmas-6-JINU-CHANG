package christmas.domain.event;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import christmas.domain.order.OrderSheet;
import christmas.dto.result.EventResult;
import christmas.dto.result.PresentEventResult;
import christmas.exception.EventNotFoundException;

public class EventPlanner {
	private final Set<Event> events;
	private static final int eventCondition = 10_000;

	public EventPlanner(Set<Event> events) {
		this.events = new HashSet<>(events);
	}

	public PresentEventResult getPresentEventResult(OrderSheet orderSheet) {
		PresentEvent presentEvent = events.stream()
			.filter(PresentEvent.class::isInstance)
			.map(PresentEvent.class::cast)
			.findFirst().orElseThrow(EventNotFoundException::new);

		return (PresentEventResult)presentEvent.getEventBenefits(orderSheet);
	}

	public Set<EventResult> calculate(OrderSheet orderSheet) {
		if (isSatisfiedBy(orderSheet)) {
			return events.stream()
				.map(event-> event.getEventBenefits(orderSheet))
				.filter(eventResult -> eventResult!=null)
				.collect(Collectors.toSet());
		}
		return null;
	}

	public int calculateTotalBenefits(Set<EventResult> results) {
		return results.stream().mapToInt(result -> result.getBenefit()).sum();
	}

	private boolean isSatisfiedBy(OrderSheet orderSheet) {
		return orderSheet.getTotalPayment() >= eventCondition;
	}
}
