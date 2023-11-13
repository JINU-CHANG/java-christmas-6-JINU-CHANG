package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.IllegalNumberException;

public class InputView {
	private static final String ASK_VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
	private static final String ASK_ORDERS_MESSAGE = "주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

	public static int readVisitDate() {
		System.out.println(ASK_VISIT_DATE_MESSAGE);
		String visitDate = Console.readLine();
		if (isNotNumeric(visitDate)) {
			throw new IllegalNumberException();
		}
		return Integer.parseInt(visitDate);
	}

	public static String readOrders() {
		System.out.println(ASK_ORDERS_MESSAGE);
		return Console.readLine();
	}

	private static boolean isNotNumeric(String input) {
		return !input.matches("-?\\d+(\\.\\d+)?");
	}
}
