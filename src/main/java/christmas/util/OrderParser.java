package christmas.util;

public class OrderParser {
	private static final String ORDERS_DELIMITER = ",";
	private static final String MENU_QUANTITY_DELIMITER = "-";

	public static String[] parseOrders(String input) {
		return input.split(ORDERS_DELIMITER);
	}

	public static String parseMenuName(String order) {
		return order.split(MENU_QUANTITY_DELIMITER)[0];
	}

	public static int parseQuantity(String order) {
		String quantity = order.split(MENU_QUANTITY_DELIMITER)[1];
		return Integer.parseInt(quantity);
	}
}
