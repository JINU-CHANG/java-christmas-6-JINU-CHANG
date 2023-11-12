package christmas.domain.order;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;

import christmas.exception.IllegalDateException;

public class VisitDate {
	private static final YearMonth visitYearMonth = YearMonth.of(2023, 12);
	private final LocalDate visitDate;

	public VisitDate(int input) {
		this.visitDate = convertToLocalDate(input);
	}

	public int getVisitDate() {
		return this.visitDate.getDayOfMonth();
	}

	private LocalDate convertToLocalDate(int input) {
		try {
			return visitYearMonth.atDay(input);
		} catch (DateTimeException e) {
			throw new IllegalDateException();
		}
	}
}
