package christmas.domain;

import static christmas.exception.MenuNotFoundException.MENU_NOT_FOUND_MESSAGE;
import static java.util.Map.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.domain.menu.Menu;
import christmas.domain.order.OrderSheet;
import christmas.dto.order.OrderInput;
import christmas.dto.order.VisitDate;
import christmas.exception.MenuNotFoundException;

public class OrderSheetTest {
	@DisplayName("존재하지 않는 메뉴를 입력하는 경우 예외를 던진다.")
	@Test
	void whenMenuNotExistedThrowException() {
		String invalidOrders = "해산물파스타-1,화이트와인-1,초코케이크-1";
		VisitDate visitDate = new VisitDate(1);
		OrderInput orderInput = new OrderInput(invalidOrders);

		assertThatThrownBy(() -> new OrderSheet(visitDate, orderInput))
			.isExactlyInstanceOf(MenuNotFoundException.class)
			.hasMessageContaining(MENU_NOT_FOUND_MESSAGE);
	}

	@DisplayName("방문날짜와 주문을 입력받아 OrderForm을 생성한다.")
	@Test
	void createOrderForm() {
		String invalidOrders = "해산물파스타-1,레드와인-1,초코케이크-1";
		VisitDate visitDate = new VisitDate(1);
		OrderInput orderInput = new OrderInput(invalidOrders);

		OrderSheet orderSheet = new OrderSheet(visitDate, orderInput);

		assertThat(orderSheet.getOrders())
			.contains(entry(Menu.SEAFOOD_PASTA, 1), entry(Menu.RED_WINE, 1), entry(Menu.CHOCOLATE_CAKE, 1));
	}

	@DisplayName("할인 전 총 주문 금액을 계산한다.")
	@Test
	void calculateTotalPriceBeforeEventBenefits() {
		int sum = 0;
		sum += Menu.SEAFOOD_PASTA.getPayment(2);
		sum += Menu.RED_WINE.getPayment(1);
		sum += Menu.CHOCOLATE_CAKE.getPayment(1);

		OrderSheet orderSheet = new OrderSheet(new VisitDate(1), new OrderInput("해산물파스타-2,레드와인-1,초코케이크-1"));
		int totalPrice = orderSheet.getTotalPayment();

		assertThat(totalPrice).isEqualTo(sum);
	}
}
