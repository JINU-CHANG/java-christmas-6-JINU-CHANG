package christmas.domain.menu;

public enum Type {
	APPETIZER("에피타이저"),
	MAIN("메인"),
	DESSERT("디저트"),
	DRINK("음료");
	private String name;

	Type(String name) {
		this.name = name;
	}
}
