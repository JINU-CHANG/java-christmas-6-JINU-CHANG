package christmas.view;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import christmas.domain.menu.Menu;
import christmas.dto.badge.BadgeResult;
import christmas.dto.result.EventResult;
import christmas.dto.result.PresentEventResult;

public class OutputView {
	private static final String GREETING_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
	public static final String START_EVENT_PLANNER_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
	private static final String ORDERS_TITLE = "<주문 메뉴>";
	private static final String MENU_QUANTITY_FORMAT = "%s %d개";
	public static final String TOTAL_PAYMENT_FORMAT= "<할인 전 총주문 금액>\n%s원\n";
	public static final String PRESENT_EVENT_TITLE = "<증정 메뉴>";
	public static final String NOT_EXIST = "없음\n";
	public static final String EVENT_BENEFITS_TITLE = "<혜택 내역>";
	public static final String EVENT_BENEFITS_FORMAT = "%s: %s원";
	public static final String TOTAL_BENEFITS_FORMAT = "<총혜택 금액>\n%s원\n";
	public static final String EXPECTED_PAYMENT_FORMAT = "<할인 후 예상 결제 금액>\n%s원\n";
	public static final String EVENT_BADGE_TITLE = "<12월 이벤트 배지>";
	private static final DecimalFormat decimalFormat = new DecimalFormat("###,###");

	public static void printGreeting() {
		printMessage(GREETING_MESSAGE);
	}

	public static void printStartEventPlanner(LocalDate localDate) {
		printMessage(START_EVENT_PLANNER_MESSAGE, localDate.getDayOfMonth());
	}

	public static void printOrders(Map<Menu, Integer> orders) {
		printMessage(ORDERS_TITLE);

		orders.forEach((menu, quantity) -> printMessage(MENU_QUANTITY_FORMAT, menu.getMenuName(), quantity));
		System.out.println();
	}

	public static void printTotalPayment(int totalPayment) {
		printMessage(TOTAL_PAYMENT_FORMAT, decimalFormat.format(totalPayment));
	}

	public static void printPresentEventResult(PresentEventResult presentEventResult) {
		printMessage(PRESENT_EVENT_TITLE);
		if (presentEventResult == null) {
			printNotExist();
			return;
		}

		printMessage(MENU_QUANTITY_FORMAT, presentEventResult.getPresent(), presentEventResult.getPresentQuantity());
		System.out.println();
	}

	public static void printEventResults(Set<EventResult> events) {
		printMessage(EVENT_BENEFITS_TITLE);
		if (events == null) {
			printNotExist();
			return;
		}

		events.forEach(event -> printMessage(EVENT_BENEFITS_FORMAT, event.getName(), decimalFormat.format((-1) * event.getBenefit())));
		System.out.println();
	}

	public static void printEventBenefits(int eventBenefits) {
		printMessage(TOTAL_BENEFITS_FORMAT, decimalFormat.format((-1) * eventBenefits));
	}

	public static void printExpectedPayment(int expectedPayment) {
		printMessage(String.format(EXPECTED_PAYMENT_FORMAT, decimalFormat.format(expectedPayment)));
	}

	public static void printBadgeResult(BadgeResult badge) {
		printMessage(EVENT_BADGE_TITLE);
		if (badge == null) {
			printNotExist();
			return;
		}

		printMessage(badge.getName());
	}

	private static void printNotExist() {
		printMessage(NOT_EXIST);
	}

	private static <T> void printMessage(String format, T... args) {
		System.out.println(String.format(format, args));
	}
}
