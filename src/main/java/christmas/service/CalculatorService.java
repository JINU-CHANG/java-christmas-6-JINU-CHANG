package christmas.service;

import java.util.Set;
import java.util.stream.Collectors;

import christmas.domain.event.Event;
import christmas.domain.order.OrderSheet;
import christmas.dto.result.EventResult;

public class CalculatorService {
	public Set<EventResult> calculate(OrderSheet orderSheet, Set<Event> events) {
		return events.stream()
			.map(event-> event.getEventBenefits(orderSheet))
			.filter(eventResult -> eventResult!=null)
			.collect(Collectors.toSet());
	}

	public int calculateExpectedPayment(int totalPayment, int discountAmount) {
		return totalPayment - discountAmount;
	}

	public int calculateEventBenefits(Set<EventResult> results) {
		return results.stream().mapToInt(result -> result.getBenefit()).sum();
	}
}
