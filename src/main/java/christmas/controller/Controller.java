package christmas.controller;

import christmas.view.OutputView;

public class Controller {
	private final OutputView outputView;

	public Controller(OutputView outputView) {
		this.outputView = outputView;
	}

	public void run() {
		outputView.printGreeting();
	}
}
