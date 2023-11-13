package christmas.controller;

import java.util.Set;
import java.util.function.Supplier;

import christmas.domain.event.EventCalculator;
import christmas.domain.order.OrderForm;
import christmas.domain.order.OrderInput;
import christmas.domain.order.VisitDate;
import christmas.domain.result.EventResult;
import christmas.domain.result.PresentEventResult;
import christmas.exception.CommonIllegalArgumentException;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {
	private final OutputView outputView;
	private final InputView inputView;
	private final EventCalculator eventCalculator;

	public Controller(OutputView outputView, InputView inputView, EventCalculator eventCalculator) {
		this.outputView = outputView;
		this.inputView = inputView;
		this.eventCalculator = eventCalculator;
	}

	public void run() {
		outputView.printGreeting();

		VisitDate visitDate = tryUntilInputIsValid(() -> getVisitDate());
		OrderForm orderForm = tryUntilInputIsValid(() -> getOrders(visitDate));

		outputView.printStartEventBenefits(orderForm);

		showOrders(orderForm);
		showEventBenefits(orderForm);
	}

	private VisitDate getVisitDate() {
		return new VisitDate(inputView.readVisitDate());
	}

	private OrderForm getOrders(VisitDate visitDate) {
		OrderInput orderInput = new OrderInput(inputView.readOrders());
		return new OrderForm(visitDate, orderInput);
	}

	private void showEventBenefits(OrderForm orderForm) {
		Set<EventResult> results = eventCalculator.calculate(orderForm);
	}

	private void showOrders(OrderForm orderForm) {
		outputView.printOrders(orderForm);
		outputView.printTotalPayment(orderForm.getTotalPayment());
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
