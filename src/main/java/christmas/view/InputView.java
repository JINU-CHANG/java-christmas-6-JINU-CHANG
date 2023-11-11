package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.IllegalNumberException;

public class InputView {
	private static final String ASK_VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

	public int readVisitDate() {
		System.out.println(ASK_VISIT_DATE_MESSAGE);
		String visitDate = Console.readLine();
		if (isNotNumeric(visitDate)) {
			throw new IllegalNumberException();
		}
		return Integer.parseInt(visitDate);
	}

	private boolean isNotNumeric(String input) {
		return !input.matches("-?\\d+(\\.\\d+)?");
	}
}
