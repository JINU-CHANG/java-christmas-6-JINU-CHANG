package christmas.controller;

import java.util.Set;
import java.util.function.Supplier;

import christmas.domain.event.EventPlanner;
import christmas.domain.order.OrderSheet;
import christmas.domain.order.OrderInput;
import christmas.domain.order.VisitDate;
import christmas.domain.result.EventResult;
import christmas.domain.result.PresentEventResult;
import christmas.exception.CommonIllegalArgumentException;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {
	private final EventPlanner eventPlanner;

	public EventPlannerController(EventPlanner eventPlanner) {
		this.eventPlanner = eventPlanner;
	}

	public void run() {
		OutputView.printGreeting();

		VisitDate visitDate = tryUntilInputIsValid(() -> getVisitDate());
		OrderSheet orderSheet = tryUntilInputIsValid(() -> getOrders(visitDate));

		OutputView.printStartEventBenefits(orderSheet);

		showOrders(orderSheet);
		showEventBenefits(orderSheet);
	}

	private VisitDate getVisitDate() {
		return new VisitDate(InputView.readVisitDate());
	}

	private OrderSheet getOrders(VisitDate visitDate) {
		OrderInput orderInput = new OrderInput(InputView.readOrders());
		return new OrderSheet(visitDate, orderInput);
	}

	private void showEventBenefits(OrderSheet orderSheet) {
		OutputView.printPresentEventResult(eventPlanner.getPresentEventResult(orderSheet));

		Set<EventResult> results = eventPlanner.calculate(orderSheet);
	}

	private void showOrders(OrderSheet orderSheet) {
		OutputView.printOrders(orderSheet);
		OutputView.printTotalPayment(orderSheet.getTotalPayment());
	}

	private <T> T tryUntilInputIsValid(Supplier<T> function) {
		try {
			return function.get();
		} catch (CommonIllegalArgumentException e) {
			System.out.println(e.getMessage());
			return tryUntilInputIsValid(function);
		}
	}
}
