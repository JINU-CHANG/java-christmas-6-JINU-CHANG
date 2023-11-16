package christmas.domain.badge;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Badge {
	STAR("별", benefits -> (5_000 <= benefits && benefits < 10_000)),
	TREE("트리", benefits -> (10_000 <= benefits && benefits < 20_000)),
	SANTA("산타", benefits -> (20_000 <= benefits));

	private String name;
	private Predicate<Integer> condition;

	Badge(String name, Predicate<Integer> condition) {
		this.name = name;
		this.condition = condition;
	}

	public static Badge of(int benefits) {
		return Arrays.stream(Badge.values())
				.filter(badge -> badge.condition.test(benefits))
				.findFirst()
				.orElse(null);
	}

	public String getName() {
		return name;
	}
}
