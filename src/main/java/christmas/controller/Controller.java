package christmas.controller;

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
		inputView.readVisitDate();
	}
}
