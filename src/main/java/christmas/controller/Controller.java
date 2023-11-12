package christmas.controller;

import java.util.function.Supplier;

import christmas.domain.order.OrderForm;
import christmas.domain.order.OrderInput;
import christmas.domain.order.VisitDate;
import christmas.exception.CommonIllegalArgumentException;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {
	private final OutputView outputView;
	private final InputView inputView;

	public Controller(OutputView outputView, InputView inputView) {
		this.outputView = outputView;
		this.inputView = inputView;
	}

	public void run() {
		outputView.printGreeting();
		VisitDate visitDate = tryUntilInputIsValid(() -> getVisitDate());
		OrderForm orderForm = tryUntilInputIsValid(() -> getOrders(visitDate));
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
		outputView.printStartEventBenefits(orderForm);
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
