package christmas.domain.event;

import java.time.DayOfWeek;
import java.time.LocalDate;

import christmas.domain.menu.MenuType;
import christmas.domain.order.OrderSheet;
import christmas.dto.result.EventResult;
import christmas.dto.result.WeekendEventResult;

public class WeekdayEvent extends Event implements Discountable{
	private static final MenuType applicableMenuType = MenuType.DESSERT;
	private static final int discount = -2_023;

	public WeekdayEvent(EventType eventType, LocalDate startDate, LocalDate endDate) {
		super(eventType, startDate, endDate);
	}

	@Override
	protected boolean isSatisfiedBy(OrderSheet orderSheet) {
		return isDayOfWeekInDuration(orderSheet.getVisitDate()) && isWeekDay(orderSheet.getVisitDate());
	}

	@Override
	public EventResult getEventBenefits(OrderSheet orderSheet) {
		if (isSatisfiedBy(orderSheet)) {
			int benefitSum = orderSheet.getOrders().entrySet().stream()
				.filter(order -> order.getKey().getType().equals(applicableMenuType))
				.mapToInt(order -> order.getValue() * discount)
				.sum();

			return new WeekendEventResult(eventType.getName(), benefitSum);
		}

		return null;
	}

	private boolean isWeekDay(LocalDate localDate) {
		return !(localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || localDate.getDayOfWeek().equals(DayOfWeek.FRIDAY));
	}
}
