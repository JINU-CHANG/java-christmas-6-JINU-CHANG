package christmas.domain;

import static christmas.exception.MenuNotFoundException.MENU_NOT_FOUND_MESSAGE;
import static java.util.Map.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.exception.MenuNotFoundException;
import christmas.util.OrderValidator;

public class OrderFormTest {
	@DisplayName("존재하지 않는 메뉴를 입력하는 경우 예외를 던진다.")
	@Test
	void whenMenuNotExistedThrowException() {
		String invalidOrders = "해산물파스타-1,화이트와인-1,초코케이크-1";
		VisitDate visitDate = new VisitDate(1);
		OrderInput orderInput = new OrderInput(invalidOrders);

		assertThatThrownBy(() -> new OrderForm(visitDate, orderInput))
			.isExactlyInstanceOf(MenuNotFoundException.class)
			.hasMessageContaining(MENU_NOT_FOUND_MESSAGE);
	}

	@DisplayName("방문날짜와 주문을 입력받아 OrderForm을 생성한다.")
	@Test
	void createOrderForm() {
		String invalidOrders = "해산물파스타-1,레드와인-1,초코케이크-1";
		VisitDate visitDate = new VisitDate(1);
		OrderInput orderInput = new OrderInput(invalidOrders);

		OrderForm orderForm = new OrderForm(visitDate, orderInput);

		assertThat(orderForm.getOrders())
			.contains(entry(Menu.SEAFOOD_PASTA, 1), entry(Menu.RED_WINE, 1), entry(Menu.CHOCOLATE_CAKE, 1));
	}
}
