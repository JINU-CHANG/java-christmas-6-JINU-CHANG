package christmas.domain.event;

import christmas.domain.order.OrderForm;
import christmas.domain.result.EventResult;

abstract class Event {
	abstract String getEventName();
	abstract boolean isMatch(OrderForm orderForm);
	abstract EventResult getEventBenefits(OrderForm orderForm);
}
