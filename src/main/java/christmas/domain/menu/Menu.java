package christmas.domain.menu;

import java.util.Arrays;

import christmas.exception.MenuNotFoundException;

public enum Menu {
	MUSHROOM_SOUP(MenuType.APPETIZER, "양송이수프", 6_000),
	TAPAS(MenuType.APPETIZER, "타파스", 5_500),
	CAESAR_SALAD(MenuType.APPETIZER, "시저샐러드", 8_000),
	T_BONE_STEAK(MenuType.MAIN, "티본스테이크", 55_000),
	BARBECUE_RIB(MenuType.MAIN, "바비큐립", 54_000),
	SEAFOOD_PASTA(MenuType.MAIN, "해산물파스타", 35_000),
	CHRISTMAS_PASTA(MenuType.MAIN, "크리스마스파스타", 25_000),
	CHOCOLATE_CAKE(MenuType.DESSERT, "초코케이크", 15_000),
	ICECREAM(MenuType.DESSERT, "아이스크림", 5_000),
	ZERO_COKE(MenuType.DRINK, "제로콜라", 3_000),
	RED_WINE(MenuType.DRINK, "레드와인", 60_000),
	CHAMPAGNE(MenuType.DRINK, "샴페인", 25_000);
	private MenuType type;
	private String name;
	private int price;

	Menu(MenuType type, String name, int price) {
		this.type = type;
		this.name = name;
		this.price = price;
	}

	public static Menu from(String input) {
		return Arrays.stream(Menu.values())
			.filter(menu -> menu.name.equals(input))
			.findFirst()
			.orElseThrow(MenuNotFoundException::new);
	}

	public MenuType getType() {
		return this.type;
	}

	public String getMenuName() {
		return this.name;
	}

	public int getPrice() {
		return this.price;
	}

	public int getPayment(int quantity) {
		return (this.price * quantity);
	}

	public static boolean isMatchTo(String input, MenuType type) {
		return Arrays.stream(Menu.values())
			.anyMatch(menu -> menu.name.equals(input) && menu.type.equals(type));
	}
}
