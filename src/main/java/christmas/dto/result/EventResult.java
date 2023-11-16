package christmas.dto.result;

public class EventResult {
	private final String name;
	private final int benefit;

	public EventResult(String name, int benefit) {
		this.name = name;
		this.benefit = benefit;
	}

	public String getName() {
		return name;
	}

	public int getBenefit() {
		return benefit;
	}
}
