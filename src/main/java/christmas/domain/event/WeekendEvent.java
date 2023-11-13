package christmas.domain.event;

import java.time.DayOfWeek;
import java.time.LocalDate;

import christmas.domain.menu.MenuType;
import christmas.domain.order.OrderForm;
import christmas.domain.result.EventResult;
import christmas.domain.result.WeekendEventResult;

public class WeekendEvent extends Event{
	private final EventType eventType = EventType.WEEKEND;
	private static final MenuType applicableMenuType = MenuType.MAIN;
	private static final int discount = 2_023;

	@Override
	public boolean isMatch(OrderForm orderForm) {
		return eventType.isDayOfWeekInDuration(orderForm.getVisitDate()) && isWeekend(orderForm.getVisitDate());
	}

	@Override
	public EventResult getEventBenefits(OrderForm orderForm) {
		int benefitSum = orderForm.getOrders().entrySet().stream()
			.filter(order -> order.getKey().getType().equals(applicableMenuType))
			.mapToInt(order -> order.getValue() * discount)
			.sum();

		return new WeekendEventResult(eventType.getName(), benefitSum);
	}

	private boolean isWeekend(LocalDate localDate) {
		return localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || localDate.getDayOfWeek().equals(DayOfWeek.FRIDAY);
	}
}
