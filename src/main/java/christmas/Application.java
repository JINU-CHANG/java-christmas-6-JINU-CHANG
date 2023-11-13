package christmas;

import christmas.controller.EventPlannerController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        EventPlannerController controller = new EventPlannerController(appConfig.createEventPlanner());
        controller.run();
    }
}
