package christmas;

import java.time.LocalDate;
import java.util.Set;

import christmas.domain.event.ChristmasDDayEvent;
import christmas.domain.event.Event;
import christmas.service.CalculatorService;
import christmas.service.EventPlannerService;
import christmas.domain.event.EventType;
import christmas.domain.event.PresentEvent;
import christmas.domain.event.SpecialEvent;
import christmas.domain.event.WeekdayEvent;
import christmas.domain.event.WeekendEvent;

public class AppConfig {
	public EventPlannerService createEventPlanner() {
		return new EventPlannerService(createEvents(), new CalculatorService());
	}
	public Set<Event> createEvents() {
		ChristmasDDayEvent christmasDDayEvent = new ChristmasDDayEvent(EventType.CHRISTMAS_DDAY,
			LocalDate.of(2023, 12, 1),
			LocalDate.of(2023, 12, 25));

		PresentEvent presentEvent = new PresentEvent(EventType.PRESENT,
			LocalDate.of(2023, 12, 1),
			LocalDate.of(2023, 12, 31));

		WeekdayEvent weekdayEvent = new WeekdayEvent(EventType.WEEKDAY,
			LocalDate.of(2023, 12, 1),
			LocalDate.of(2023, 12, 31));

		WeekendEvent weekendEvent = new WeekendEvent(EventType.WEEKEND,
			LocalDate.of(2023, 12, 1),
			LocalDate.of(2023, 12, 31));

		SpecialEvent specialEvent = new SpecialEvent(EventType.SPECIAL,
			LocalDate.of(2023, 12, 1),
			LocalDate.of(2023, 12, 31));

		return Set.of(christmasDDayEvent, presentEvent, weekendEvent, weekdayEvent, specialEvent);
	}
}
