package christmas.domain;

import java.util.Arrays;

import christmas.exception.MenuNotFoundException;

public enum Menu {
	MUSHROOM_SOUP(Type.APPETIZER, "양송이수프", 6_000),
	TAPAS(Type.APPETIZER, "타파스", 5_500),
	CAESAR_SALAD(Type.APPETIZER, "시저샐러드", 8_000),
	T_BONE_STEAK(Type.MAIN, "티본스테이크", 55_000),
	BARBECUE_RIB(Type.MAIN, "바비큐립", 54_000),
	SEAFOOD_PASTA(Type.MAIN, "해산물파스타", 35_000),
	CHRISTMAS_PASTA(Type.MAIN, "크리스마스파스타", 25_000),
	CHOCOLATE_CAKE(Type.DESSERT, "초코케이크", 15_000),
	ICECREAM(Type.DESSERT, "아이스크림", 5_000),
	ZERO_COKE(Type.DRINK, "제로콜라", 3_000),
	RED_WINE(Type.DRINK, "레드와인", 60_000),
	CHAMPAGNE(Type.DRINK, "샴페인", 25_000);
	private Type type;
	private String name;
	private int price;

	Menu(Type type, String name, int price) {
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
}
