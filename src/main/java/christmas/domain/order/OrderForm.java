package christmas.domain.order;

import java.time.LocalDate;
import java.util.Map;

import christmas.domain.menu.Menu;
import christmas.util.OrderConvertor;
import christmas.validator.OrderValidator;

public class OrderForm {
	private final VisitDate visitDate;
	private final Map<Menu, Integer> orders;

	public OrderForm(VisitDate visitDate, OrderInput input) {
		this.visitDate = visitDate;
		this.orders = convertAndValidateInput(input);
	}

	public Map<Menu, Integer> getOrders() {
		return orders;
	}

	public LocalDate getVisitDate() {
		return visitDate.getVisitDate();
	}

	public int getTotalPayment() {
		return orders.entrySet()
			.stream()
			.mapToInt(entry -> ((Menu)entry.getKey()).getPayment(entry.getValue()))
			.sum();
	}

	private Map<Menu, Integer> convertAndValidateInput(OrderInput input) {
		Map<Menu, Integer> orders = OrderConvertor.convertToMap(input);
		OrderValidator.validateOrderOnlyDrinks(orders);
		return orders;
	}
}
