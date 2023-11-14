package christmas.view;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import christmas.domain.menu.Menu;
import christmas.domain.order.OrderSheet;
import christmas.dto.result.EventResult;
import christmas.dto.result.PresentEventResult;

public class OutputView {
	private static final String GREETING_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
	public static final String EVENT_BENEFITS_START_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
	private static final String ORDERS_PRINT_TITLE = "<주문 메뉴>";
	private static final String MENU_QUANTITY_FORMAT = "%s %d개";
	public static final String PAYMENT_BEFORE_EVENT_FORMAT= "<할인 전 총주문 금액>\n%s원";
	public static final String PRESENT_EVENT_FORMAT = "<증정 메뉴>";
	public static final String NOT_EXIST = "없음\n";
	public static final String EVENT_BENEFITS_TITLE = "<혜택 내역>";
	public static final String EVENT_BENEFITS_FORMAT = "%s: %s원";
	public static final String BENEFITS_FORMAT = "<총혜택 금액>\n%s원";
	public static final String PAYMENT_AFTER_EVENT_EVENT_FORMAT = "<할인 후 예상 결제 금액>\n%s원";
	private static final DecimalFormat decimalFormat = new DecimalFormat("###,###");

	public static void printGreeting() {
		System.out.println(GREETING_MESSAGE);
	}

	public static void printStartEventPlanner(LocalDate localDate) {
		System.out.println(String.format(EVENT_BENEFITS_START_MESSAGE, localDate.getDayOfMonth()));
	}

	public static void printOrders(Map<Menu, Integer> orders) {
		System.out.println(ORDERS_PRINT_TITLE);

		orders.forEach((menu, quantity) -> System.out.println(String.format(MENU_QUANTITY_FORMAT, menu.getMenuName(), quantity)));
		System.out.println();
	}

	public static void printTotalPayment(int totalPayment) {
		System.out.println(String.format(PAYMENT_BEFORE_EVENT_FORMAT, decimalFormat.format(totalPayment)));
		System.out.println();
	}

	public static void printPresentEventResult(PresentEventResult presentEventResult) {
		System.out.println(PRESENT_EVENT_FORMAT);

		if (presentEventResult == null) {
			printNotExist();
			return;
		}

		System.out.println(String.format(MENU_QUANTITY_FORMAT,
			presentEventResult.getPresent(),
			presentEventResult.getPresentQuantity()));
		System.out.println();
	}

	public static void printEventResults(Set<EventResult> events) {
		System.out.println(EVENT_BENEFITS_TITLE);

		if (events == null) {
			printNotExist();
			return;
		}

		events.stream().forEach(result ->
					System.out.println(
						String.format(EVENT_BENEFITS_FORMAT,
						result.getName(), decimalFormat.format(result.getBenefit()))));
		System.out.println();
	}

	public static void printEventBenefits(int eventBenefits) {
		System.out.println(String.format(BENEFITS_FORMAT, decimalFormat.format(eventBenefits)));
		System.out.println();
	}

	private static void printNotExist() {
		System.out.println(NOT_EXIST);
		System.out.println();
	}

	public static void printExpectedPayment(int expectedPayment) {
		System.out.println(String.format(PAYMENT_AFTER_EVENT_EVENT_FORMAT, decimalFormat.format(expectedPayment)));
		System.out.println();
	}
}
