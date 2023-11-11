package christmas.controller;

import java.util.function.Supplier;

import christmas.domain.OrderForm;
import christmas.domain.OrderInput;
import christmas.domain.OrderValidator;
import christmas.domain.VisitDate;
import christmas.exception.CommonIllegalArgumentException;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {
	private final OutputView outputView;
	private final InputView inputView;
	private final OrderValidator orderValidator;

	public Controller(OutputView outputView, InputView inputView, OrderValidator orderValidator) {
		this.outputView = outputView;
		this.inputView = inputView;
		this.orderValidator = orderValidator;
	}

	public void run() {
		outputView.printGreeting();
		VisitDate visitDate = tryUntilInputIsValid(this::getVisitDate);
		tryUntilInputIsValid(() -> getOrders(visitDate));
	}

	private VisitDate getVisitDate() {
		return new VisitDate(inputView.readVisitDate());
	}

	private OrderForm getOrders(VisitDate visitDate) {
		OrderInput orderInput = new OrderInput(inputView.readOrders(), orderValidator);
		return new OrderForm(visitDate, orderInput);
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
