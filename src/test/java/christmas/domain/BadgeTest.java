package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import christmas.domain.badge.Badge;

public class BadgeTest {

	@DisplayName("총혜택에 따라 별 뱃지를 제공한다.")
	@ParameterizedTest
	@ValueSource(ints = {5_000, 6_000, 7_000, 8_000, 9_000})
	void testStarBadgeByTotalBenefits(int input) {
		assertThat(Badge.of(input)).isEqualTo(Badge.STAR);
	}

	@DisplayName("총혜택에 따라 트리 뱃지를 제공한다.")
	@ParameterizedTest
	@ValueSource(ints = {10_000, 11_000, 12_000, 18_000, 19_000})
	void testTreeBadgeByTotalBenefits(int input) {
		assertThat(Badge.of(input)).isEqualTo(Badge.TREE);
	}

	@DisplayName("총혜택에 따라 산타 뱃지를 제공한다.")
	@ParameterizedTest
	@ValueSource(ints = {20_000, 21_000})
	void testSantaBadgeByTotalBenefits(int input) {
		assertThat(Badge.of(input)).isEqualTo(Badge.SANTA);
	}

	@DisplayName("해당되는 뱃지가 없으면 null을 반환한다.")
	@ParameterizedTest
	@ValueSource(ints = {1_000, 2_000})
	void testNull(int input) {
		assertThat(Badge.of(input)).isEqualTo(null);
	}
}
