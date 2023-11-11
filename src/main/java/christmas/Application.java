package christmas;

import christmas.controller.Controller;
import christmas.util.OrderValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller(new OutputView(), new InputView());
        controller.run();
    }
}
