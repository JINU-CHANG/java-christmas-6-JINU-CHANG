package christmas.controller;

import java.util.function.Supplier;

import christmas.domain.VisitDate;
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
		tryUntilInputIsValid(this::getVisitDate);
	}

	private VisitDate getVisitDate() {
		return new VisitDate(inputView.readVisitDate());
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
