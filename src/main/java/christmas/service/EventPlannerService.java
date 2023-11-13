package christmas.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import christmas.domain.badge.Badge;
import christmas.domain.event.Event;
import christmas.domain.event.PresentEvent;
import christmas.domain.order.OrderSheet;
import christmas.dto.badge.BadgeResult;
import christmas.dto.order.OrderInput;
import christmas.dto.order.VisitDate;
import christmas.dto.result.EventResult;
import christmas.dto.result.PresentEventResult;
import christmas.exception.EventNotFoundException;

public class EventPlannerService {
	private final Set<Event> events;
	private static final int eventCondition = 10_000;

	public EventPlannerService(Set<Event> events) {
		this.events = new HashSet<>(events);
	}

	public OrderSheet createOrderSheet(VisitDate visitDate, OrderInput orderInput) {
		return new OrderSheet(visitDate, orderInput);
	}

	public PresentEventResult getPresentEventResult(OrderSheet orderSheet) {
		PresentEvent presentEvent = events.stream()
			.filter(PresentEvent.class::isInstance)
			.map(PresentEvent.class::cast)
			.findFirst().orElseThrow(EventNotFoundException::new);

		return (PresentEventResult)presentEvent.getEventBenefits(orderSheet);
	}

	public Set<EventResult> getEventResult(OrderSheet orderSheet) {
		if (isSatisfiedBy(orderSheet)) {
			return calculate(orderSheet);
		}
		return null;
	}

	public int getTotalBenefits(Set<EventResult> results) {
		return calculateTotalBenefits(results);
	}

	public int calculateTotalBenefits(Set<EventResult> results) {
		return results.stream().mapToInt(result -> result.getBenefit()).sum();
	}

	public int getExpectedPayment(OrderSheet orderSheet, Set<EventResult> results) {
		return orderSheet.getTotalPayment() + calculateTotalBenefits(results);
	}
	public BadgeResult getBadge(int totalBenefits) {
		return Optional.ofNullable(Badge.of(totalBenefits))
			.map(badge -> new BadgeResult(badge.getName()))
			.orElse(null);
	}

	private Set<EventResult> calculate(OrderSheet orderSheet) {
		return events.stream()
			.map(event-> event.getEventBenefits(orderSheet))
			.filter(eventResult -> eventResult!=null)
			.collect(Collectors.toSet());
	}

	private boolean isSatisfiedBy(OrderSheet orderSheet) {
		return orderSheet.getTotalPayment() >= eventCondition;
	}
}
