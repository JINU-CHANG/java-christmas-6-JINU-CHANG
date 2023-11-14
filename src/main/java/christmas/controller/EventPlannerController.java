package christmas.controller;

import java.util.Set;
import java.util.function.Supplier;

import christmas.domain.event.PresentEvent;
import christmas.dto.result.PresentEventResult;
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

		OrderSheet orderSheet = getOrders();

		OutputView.printStartEventPlanner(orderSheet.getVisitDate());

		showOrders(orderSheet);
		showEventBenefits(orderSheet);
	}

	private OrderSheet getOrders() {
		VisitDate visitDate = tryUntilInputIsValid(this::getVisitDate);
		OrderInput orderInput = tryUntilInputIsValid(this::getOrderInput);
		return eventPlannerService.createOrderSheet(visitDate, orderInput);
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
		PresentEventResult presentEventResult = eventPlannerService.getSpecificEventResult(orderSheet, PresentEvent.class);
		OutputView.printPresentEventResult(presentEventResult);

		Set<EventResult> results = eventPlannerService.getEventResults(orderSheet);
		OutputView.printEventResults(results);

		int totalBenefits = eventPlannerService.getTotalBenefits(results);
		OutputView.printEventBenefits(totalBenefits);

		int expectedPayment = eventPlannerService.getExpectedPayment(orderSheet);
		OutputView.printExpectedPayment(expectedPayment);

		showBadge(totalBenefits);
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
