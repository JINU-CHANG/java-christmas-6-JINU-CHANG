package christmas.domain.event;

import christmas.domain.order.OrderSheet;
import christmas.domain.result.EventResult;

abstract class Event {
	abstract boolean isSatisfiedBy(OrderSheet orderSheet);
	abstract EventResult getEventBenefits(OrderSheet orderSheet);
}
