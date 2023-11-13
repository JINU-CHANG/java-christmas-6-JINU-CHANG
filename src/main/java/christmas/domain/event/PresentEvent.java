package christmas.domain.event;

import java.time.LocalDate;

import christmas.domain.menu.Menu;
import christmas.domain.order.OrderSheet;
import christmas.domain.result.EventResult;
import christmas.domain.result.PresentEventResult;

public class PresentEvent extends Event{
	private final Menu present = Menu.CHAMPAGNE;
	private static final int presentQuantity = 1;
	private static final int requiredPayment = 120_000;

	public PresentEvent(EventType eventType, LocalDate startDate, LocalDate endDate) {
		super(eventType, startDate, endDate);
	}

	@Override
	public boolean isSatisfiedBy(OrderSheet orderSheet) {
		return isDayOfWeekInDuration(orderSheet.getVisitDate()) && isMoreThanStandardPayment(orderSheet.getTotalPayment());
	}

	@Override
	public EventResult getEventBenefits(OrderSheet orderSheet) {
		return new PresentEventResult(eventType.getName(), present.getPrice(), Menu.CHAMPAGNE, presentQuantity);
	}

	private boolean isMoreThanStandardPayment(int totalPayment) {
		return totalPayment >= requiredPayment;
	}
}
