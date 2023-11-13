package christmas.domain.order;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;

import christmas.exception.IllegalDateException;

public class VisitDate {
	private final YearMonth visitYearMonth = YearMonth.of(2023, 12);
	private final LocalDate visitDate;

	public VisitDate(int input) {
		this.visitDate = convertBy(input);
	}

	public LocalDate getVisitDate() {
		return this.visitDate;
	}

	private LocalDate convertBy(int input) {
		try {
			return visitYearMonth.atDay(input);
		} catch (DateTimeException e) {
			throw new IllegalDateException();
		}
	}
}
