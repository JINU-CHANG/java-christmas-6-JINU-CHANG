package christmas.domain.event;

import java.time.LocalDate;

import christmas.domain.order.OrderSheet;
import christmas.dto.result.ChristmasDDayEventResult;
import christmas.dto.result.EventResult;

public class ChristmasDDayEvent extends Event implements Discountable{
	private static final int baseDiscount = 1_000;
	private static final int increment = 100;

	public ChristmasDDayEvent(EventType eventType, LocalDate startDate, LocalDate endDate) {
		super(eventType, startDate, endDate);
	}

	@Override
	public boolean isNotSatisfiedBy(OrderSheet orderSheet) {
		return isDayOfWeekNotInDuration(orderSheet.getVisitDate());
	}

	@Override
	public EventResult getEventBenefits(OrderSheet orderSheet) {
		if (isNotSatisfiedBy(orderSheet)) {
			return null;
		}

		return new ChristmasDDayEventResult(eventType.getName(), calculateDiscount(orderSheet));
	}

	private int calculateDiscount(OrderSheet orderSheet) {
		return (baseDiscount + (getDuration(orderSheet) * increment));
	}

	private int getDuration(OrderSheet orderSheet){
		return orderSheet.getVisitDate().getDayOfMonth() - startDate.getDayOfMonth();
	}
}
