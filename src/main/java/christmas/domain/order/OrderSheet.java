package christmas.domain.order;

import java.time.LocalDate;
import java.util.Map;

import christmas.domain.menu.Menu;
import christmas.dto.order.OrderInput;
import christmas.dto.order.VisitDate;
import christmas.util.OrderConvertor;

public class OrderSheet {
	private final VisitDate visitDate;
	private final Map<Menu, Integer> orders;

	public OrderSheet(VisitDate visitDate, OrderInput input) {
		this.visitDate = visitDate;
		this.orders = convertBy(input);
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
			.mapToInt(entry -> entry.getKey().getPayment(entry.getValue()))
			.sum();
	}

	private Map<Menu, Integer> convertBy(OrderInput input) {
		return OrderConvertor.convertToMap(input);
	}
}
