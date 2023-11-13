package christmas.domain.event;

import java.time.LocalDate;

public enum EventType {
	PRESENT("증정 이벤트",
		LocalDate.of(2023, 12, 1),
		LocalDate.of(2023, 12, 31)),
	CHRISTMAS_DDAY("크리스마스 디데이 할인",
		LocalDate.of(2023, 12, 1),
		LocalDate.of(2023, 12, 25));

	private String name;
	private LocalDate startDate;
	private LocalDate endDate;

	EventType(String name, LocalDate startDate, LocalDate endDate) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public boolean isDayOfWeekInDuration(LocalDate localDate) {
		return localDate.compareTo(startDate) >=0 && localDate.compareTo(endDate) <=0;
	}

	public String getName() {
		return name;
	}
}
