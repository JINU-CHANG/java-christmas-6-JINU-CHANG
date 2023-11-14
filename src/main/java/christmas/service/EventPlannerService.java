package christmas.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import christmas.domain.badge.Badge;
import christmas.domain.event.Discountable;
import christmas.domain.event.Event;
import christmas.domain.order.OrderSheet;
import christmas.dto.badge.BadgeResult;
import christmas.dto.order.OrderInput;
import christmas.dto.order.VisitDate;
import christmas.dto.result.EventResult;

public class EventPlannerService {
	private static final int eventCondition = 10_000;
	private final CalculatorService calculatorService;
	private final Set<Event> events;

	public EventPlannerService(Set<Event> events, CalculatorService calculatorService) {
		this.events = new HashSet<>(events);
		this.calculatorService = calculatorService;
	}

	public OrderSheet createOrderSheet(VisitDate visitDate, OrderInput orderInput) {
		return new OrderSheet(visitDate, orderInput);
	}

	public <T extends Event, R extends EventResult> R getSpecificEventResult(OrderSheet orderSheet, Class<T> eventClass) {
		return (R)extractEvent(eventClass).getEventBenefits(orderSheet);
	}

	public Set<EventResult> getEventResults(OrderSheet orderSheet) {
		if (isSatisfiedBy(orderSheet)) {
			return calculatorService.calculate(orderSheet, events);
		}
		return null;
	}

	public int getExpectedPayment(OrderSheet orderSheet) {
		if (isSatisfiedBy(orderSheet)) {
			int discountAmount = getEventBenefits(calculatorService.calculate(orderSheet, extractDiscountableEvents()));
			return calculatorService.calculateExpectedPayment(orderSheet.getTotalPayment(), discountAmount);
		}
		return orderSheet.getTotalPayment();
	}

	public int getEventBenefits(Set<EventResult> results) {
		return calculatorService.calculateEventBenefits(results);
	}

	public BadgeResult getBadge(int totalBenefits) {
		return Optional.ofNullable(Badge.of(totalBenefits))
			.map(badge -> new BadgeResult(badge.getName()))
			.orElse(null);
	}

	private boolean isSatisfiedBy(OrderSheet orderSheet) {
		return orderSheet.getTotalPayment() >= eventCondition;
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
