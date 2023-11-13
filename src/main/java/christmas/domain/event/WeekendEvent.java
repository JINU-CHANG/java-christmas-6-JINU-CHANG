package christmas.domain.event;

import java.time.DayOfWeek;
import java.time.LocalDate;

import christmas.domain.menu.MenuType;
import christmas.domain.order.OrderSheet;
import christmas.domain.result.EventResult;
import christmas.domain.result.WeekendEventResult;

public class WeekendEvent extends Event{
	private static final MenuType applicableMenuType = MenuType.MAIN;
	private static final int discount = 2_023;

	public WeekendEvent(EventType eventType, LocalDate startDate, LocalDate endDate) {
		super(eventType, startDate, endDate);
	}

	@Override
	public boolean isSatisfiedBy(OrderSheet orderSheet) {
		return isDayOfWeekInDuration(orderSheet.getVisitDate()) && isWeekend(orderSheet.getVisitDate());
	}

	@Override
	public EventResult getEventBenefits(OrderSheet orderSheet) {
		int benefitSum = orderSheet.getOrders().entrySet().stream()
			.filter(order -> order.getKey().getType().equals(applicableMenuType))
			.mapToInt(order -> order.getValue() * discount)
			.sum();

		return new WeekendEventResult(eventType.getName(), benefitSum);
	}

	private boolean isWeekend(LocalDate localDate) {
		return localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || localDate.getDayOfWeek().equals(DayOfWeek.FRIDAY);
	}
}
