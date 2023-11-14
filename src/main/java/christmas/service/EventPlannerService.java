package christmas.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import christmas.domain.badge.Badge;
import christmas.domain.event.Discountable;
import christmas.domain.event.Event;
import christmas.domain.event.PresentEvent;
import christmas.domain.order.OrderSheet;
import christmas.dto.badge.BadgeResult;
import christmas.dto.order.OrderInput;
import christmas.dto.order.VisitDate;
import christmas.dto.result.EventResult;
import christmas.dto.result.PresentEventResult;

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
		return (PresentEventResult)extractEvent(PresentEvent.class).getEventBenefits(orderSheet);
	}

	public Set<EventResult> getEventResult(OrderSheet orderSheet) {
		if (isSatisfiedBy(orderSheet)) {
			return calculate(orderSheet, events);
		}
		return null;
	}

	public int getExpectedPayment(OrderSheet orderSheet) {
		if (isSatisfiedBy(orderSheet)) {
			return orderSheet.getTotalPayment() + getTotalBenefits(calculate(orderSheet, extractDiscountableEvents()));
		}
		return orderSheet.getTotalPayment();
	}

	public int getTotalBenefits(Set<EventResult> results) {
		return calculateTotalBenefits(results);
	}

	public BadgeResult getBadge(int totalBenefits) {
		return Optional.ofNullable(Badge.of(totalBenefits))
			.map(badge -> new BadgeResult(badge.getName()))
			.orElse(null);
	}

	private boolean isSatisfiedBy(OrderSheet orderSheet) {
		return orderSheet.getTotalPayment() >= eventCondition;
	}

	private Set<EventResult> calculate(OrderSheet orderSheet, Set<Event> events) {
		return events.stream()
			.map(event-> event.getEventBenefits(orderSheet))
			.filter(eventResult -> eventResult!=null)
			.collect(Collectors.toSet());
	}

	private int calculateTotalBenefits(Set<EventResult> results) {
		return results.stream().mapToInt(result -> result.getBenefit()).sum();
	}

	private Set<Event> extractDiscountableEvents() {
		return events.stream()
			.filter(Discountable.class::isInstance)
			.collect(Collectors.toSet());
	}

	private <T> T extractEvent(Class<T> eventClass) {
		return events.stream()
			.filter(eventClass::isInstance)
			.map(eventClass::cast)
			.findAny().get();
	}
}
