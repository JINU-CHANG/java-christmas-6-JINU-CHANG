package christmas.domain.event;

import java.time.DayOfWeek;
import java.time.LocalDate;

import christmas.domain.menu.MenuType;
import christmas.domain.order.OrderSheet;
import christmas.domain.result.EventResult;
import christmas.domain.result.WeekendEventResult;

public class WeekdayEvent extends Event{
	private final EventType eventType = EventType.WEEKDAY;
	private static final MenuType applicableMenuType = MenuType.DESSERT;
	private static final int discount = 2_023;

	@Override
	public boolean isSatisfiedBy(OrderSheet orderSheet) {
		return eventType.isDayOfWeekInDuration(orderSheet.getVisitDate()) && isWeekDay(orderSheet.getVisitDate());
	}

	@Override
	public EventResult getEventBenefits(OrderSheet orderSheet) {
		int benefitSum = orderSheet.getOrders().entrySet().stream()
			.filter(order -> order.getKey().getType().equals(applicableMenuType))
			.mapToInt(order -> order.getValue() * discount)
			.sum();

		return new WeekendEventResult(eventType.getName(), benefitSum);
	}

	private boolean isWeekDay(LocalDate localDate) {
		return !(localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || localDate.getDayOfWeek().equals(DayOfWeek.FRIDAY));
	}
}
