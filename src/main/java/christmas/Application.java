package christmas;

import java.util.Set;

import christmas.controller.EventPlannerController;
import christmas.domain.event.ChristmasDDayEvent;
import christmas.domain.event.EventPlanner;
import christmas.domain.event.PresentEvent;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        EventPlanner eventPlanner = new EventPlanner(Set.of(new ChristmasDDayEvent(), new PresentEvent() ));
        EventPlannerController controller = new EventPlannerController(new OutputView(), new InputView(), eventPlanner);
        controller.run();
    }
}
