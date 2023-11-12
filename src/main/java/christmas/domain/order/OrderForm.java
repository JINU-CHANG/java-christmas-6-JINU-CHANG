package christmas.domain.order;

import java.util.EnumMap;
import java.util.Map;

import christmas.domain.menu.Menu;
import christmas.util.OrderParser;

public class OrderForm {
	private final VisitDate visitDate;
	private final Map<Menu, Integer> orders;

	public OrderForm(VisitDate visitDate, OrderInput input) {
		this.visitDate = visitDate;
		this.orders = convertToMap(input);
	}

	public Map<Menu, Integer> getOrders() {
		return orders;
	}

	private Map<Menu, Integer> convertToMap(OrderInput input) {
		Map<Menu, Integer> orders = new EnumMap<>(Menu.class);

		for (String order : input.getOrders()) {
			String menuName = OrderParser.parseMenuName(order);
			int quantity = OrderParser.parseQuantity(order);

			orders.put(Menu.from(menuName), quantity);
		}

		return orders;
	}
}
