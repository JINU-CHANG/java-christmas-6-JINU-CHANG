package christmas.controller;

import java.util.Set;
import java.util.function.Supplier;

import christmas.domain.event.PresentEvent;
import christmas.service.EventPlannerService;
import christmas.domain.order.OrderSheet;
import christmas.dto.order.OrderInput;
import christmas.dto.order.VisitDate;
import christmas.dto.result.EventResult;
import christmas.exception.CommonIllegalArgumentException;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {
	private final EventPlannerService eventPlannerService;

	public EventPlannerController(EventPlannerService eventPlannerService) {
		this.eventPlannerService = eventPlannerService;
	}

	public void run() {
		OutputView.printGreeting();

		VisitDate visitDate = tryUntilInputIsValid(this::getVisitDate);
		OrderInput orderInput = tryUntilInputIsValid(this::getOrderInput);

		OrderSheet orderSheet = eventPlannerService.createOrderSheet(visitDate, orderInput);

		OutputView.printStartEventBenefits(orderSheet.getVisitDate());

		showOrders(orderSheet);
		showEventBenefits(orderSheet);
	}

	private VisitDate getVisitDate() {
		return new VisitDate(InputView.readVisitDate());
	}

	private OrderInput getOrderInput() {
		return new OrderInput(InputView.readOrders());
	}

	private void showOrders(OrderSheet orderSheet) {
		OutputView.printOrders(orderSheet.getOrders());
		OutputView.printTotalPayment(orderSheet.getTotalPayment());
	}

	private void showEventBenefits(OrderSheet orderSheet) {
		OutputView.printPresentEventResult(eventPlannerService.getSpecificEventResult(orderSheet, PresentEvent.class));

		Set<EventResult> results = eventPlannerService.getEventResults(orderSheet);
		OutputView.printEventBenefits(results);

		int totalBenefits = eventPlannerService.getTotalBenefits(results);
		OutputView.printTotalBenefits(eventPlannerService.getTotalBenefits(results));
		OutputView.printExpectedPayment(eventPlannerService.getExpectedPayment(orderSheet));
	}

	private void showBadge(int totalBenefits) {
		eventPlannerService.getBadge(totalBenefits);
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
