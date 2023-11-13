package christmas.view;

import java.text.DecimalFormat;

import christmas.domain.order.OrderForm;

public class OutputView {
	private static final String GREETING_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
	public static final String EVENT_BENEFITS_START_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
	private static final String ORDERS_PRINT_TITLE = "<주문 메뉴>";
	private static final String ORDERS_PRINT_FORMAT = "%s %d개";
	public static final String PAYMENT_BEFORE_EVENT_TITLE = "<할인 전 총주문 금액>";
	private static final DecimalFormat decimalFormat = new DecimalFormat("###,###");

	public void printGreeting() {
		System.out.println(GREETING_MESSAGE);
	}

	public void printStartEventBenefits(OrderForm orderForm) {
		System.out.println(String.format(EVENT_BENEFITS_START_MESSAGE, orderForm.getVisitDate().getDayOfMonth()));
	}

	public void printOrders(OrderForm orderForm) {
		System.out.println(ORDERS_PRINT_TITLE);

		orderForm.getOrders().forEach((menu, quantity) -> {
			System.out.println(String.format(ORDERS_PRINT_FORMAT, menu.getMenuName(), quantity));
		});
		System.out.println();
	}

	public void printTotalPayment(int totalPayment) {
		System.out.printf("%s%n%s%n", PAYMENT_BEFORE_EVENT_TITLE, decimalFormat.format(totalPayment));
	}
}
