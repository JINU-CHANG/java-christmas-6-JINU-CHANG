package christmas;

import christmas.controller.Controller;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();
        Controller controller = new Controller(outputView, inputView);
        controller.run();
    }
}
