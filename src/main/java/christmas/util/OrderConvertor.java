package christmas.util;

import java.util.EnumMap;
import java.util.Map;

import christmas.domain.menu.Menu;
import christmas.dto.order.OrderInput;

public class OrderConvertor {
	public static Map<Menu, Integer> convertToMap(OrderInput input) {
		Map<Menu, Integer> orders = new EnumMap<>(Menu.class);
		for (String order : input.getOrders()) {
			String menuName = OrderParser.parseMenuName(order);
			int quantity = OrderParser.parseQuantity(order);

			orders.put(Menu.from(menuName), quantity);
		}

		return orders;
	}
}
