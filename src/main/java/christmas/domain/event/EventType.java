package christmas.domain.event;

public enum EventType {
	PRESENT("증정 이벤트"),
	CHRISTMAS_DDAY("크리스마스 디데이 할인"),
	WEEKEND("주말 할인"),
	WEEKDAY("평일 할인"),
	SPECIAL("특별 할인");

	private String name;

	EventType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
