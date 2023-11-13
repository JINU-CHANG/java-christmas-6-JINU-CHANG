package christmas;

import java.util.Set;

import christmas.controller.Controller;
import christmas.domain.event.EventCalculator;
import christmas.domain.event.PresentEvent;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        EventCalculator eventCalculator = new EventCalculator(Set.of(new PresentEvent()));
        Controller controller = new Controller(new OutputView(), new InputView(), eventCalculator);
        controller.run();
    }
}
