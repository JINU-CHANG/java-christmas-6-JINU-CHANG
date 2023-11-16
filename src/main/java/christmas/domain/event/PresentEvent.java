package christmas.domain.event;

import java.time.LocalDate;

import christmas.domain.menu.Menu;
import christmas.domain.order.OrderSheet;
import christmas.dto.result.EventResult;
import christmas.dto.result.PresentEventResult;

public class PresentEvent extends Event{
	private final Menu present = Menu.CHAMPAGNE;
	private static final int presentQuantity = 1;
	private static final int requiredPayment = 120_000;

	public PresentEvent(EventType eventType, LocalDate startDate, LocalDate endDate) {
		super(eventType, startDate, endDate);
	}

	@Override
	protected boolean isNotSatisfiedBy(OrderSheet orderSheet) {
		return isDayOfWeekNotInDuration(orderSheet.getVisitDate()) || isLessThanRequiredPayment(orderSheet.getTotalPayment());
	}

	@Override
	public EventResult getEventBenefits(OrderSheet orderSheet) {
		if (isNotSatisfiedBy(orderSheet)) {
			return null;
		}

		return new PresentEventResult(eventType.getName(), present.getPrice(), Menu.CHAMPAGNE, presentQuantity);
	}

	private boolean isLessThanRequiredPayment(int totalPayment) {
		return totalPayment < requiredPayment;
	}
}
