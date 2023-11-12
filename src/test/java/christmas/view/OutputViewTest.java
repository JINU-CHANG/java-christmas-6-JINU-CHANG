package christmas.view;

import static christmas.view.OutputView.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.domain.order.OrderForm;
import christmas.domain.order.OrderInput;
import christmas.domain.order.VisitDate;

public class OutputViewTest {
	private final ByteArrayOutputStream output = new ByteArrayOutputStream();

	@BeforeEach
	public void setUpStreams() {
		System.setOut(new PrintStream(output));
	}

	@AfterEach
	public void restoreStreams() {
		System.setOut(System.out);
		output.reset();
	}

	@DisplayName("방문할 날짜와 함께 이벤트 혜택 미리 보기 메시지를 출력한다.")
	@Test
	void testPrintStartEventBenefits() {
		int visitDate = 1;
		OrderForm orderForm = new OrderForm(new VisitDate(visitDate), new OrderInput("해산물파스타-2,레드와인-1,초코케이크-1"));
		OutputView outputView = new OutputView();
		outputView.printStartEventBenefits(orderForm);

		assertThat(output.toString().trim()).isEqualTo(String.format(EVENT_BENEFITS_START_MESSAGE, visitDate).trim());
	}

	@DisplayName("주문 출력 형식을 확인한다. 이때 출력 순서는 고려하지 않는다.")
	@Test
	void testPrintOrders() {
		OrderForm orderForm = new OrderForm(new VisitDate(1), new OrderInput("해산물파스타-2,레드와인-1,초코케이크-1"));
		OutputView outputView = new OutputView();
		outputView.printOrders(orderForm);

		String[] expectedOrdersFormat = {"해산물파스타 2개", "레드와인 1개", "초코케이크 1개"};

		assertThat(output.toString().split("\n")).contains(expectedOrdersFormat);
	}

	@DisplayName("할인 전 주문 금액 출력한다.")
	@Test
	void testPrintTotalPayment() {
		OutputView outputView = new OutputView();
		outputView.printTotalPayment(15000);

		assertThat(output.toString().trim()).isEqualTo((PAYMENT_BEFORE_EVENT_TITLE+"\n15,000\n").trim());
	}
}
