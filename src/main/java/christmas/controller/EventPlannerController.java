package christmas.controller;

import java.util.Set;
import java.util.function.Supplier;

import christmas.domain.event.EventPlanner;
import christmas.domain.order.OrderSheet;
import christmas.domain.order.OrderInput;
import christmas.domain.order.VisitDate;
import christmas.domain.result.EventResult;
import christmas.exception.CommonIllegalArgumentException;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {
	private final OutputView outputView;
	private final InputView inputView;
	private final EventPlanner eventPlanner;

	public EventPlannerController(OutputView outputView, InputView inputView, EventPlanner eventPlanner) {
		this.outputView = outputView;
		this.inputView = inputView;
		this.eventPlanner = eventPlanner;
	}

	public void run() {
		outputView.printGreeting();

		VisitDate visitDate = tryUntilInputIsValid(() -> getVisitDate());
		OrderSheet orderSheet = tryUntilInputIsValid(() -> getOrders(visitDate));

		outputView.printStartEventBenefits(orderSheet);

		showOrders(orderSheet);
		showEventBenefits(orderSheet);
	}

	private VisitDate getVisitDate() {
		return new VisitDate(inputView.readVisitDate());
	}

	private OrderSheet getOrders(VisitDate visitDate) {
		OrderInput orderInput = new OrderInput(inputView.readOrders());
		return new OrderSheet(visitDate, orderInput);
	}

	private void showEventBenefits(OrderSheet orderSheet) {
		Set<EventResult> results = eventPlanner.calculate(orderSheet);
	}

	private void showOrders(OrderSheet orderSheet) {
		outputView.printOrders(orderSheet);
		outputView.printTotalPayment(orderSheet.getTotalPayment());
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
