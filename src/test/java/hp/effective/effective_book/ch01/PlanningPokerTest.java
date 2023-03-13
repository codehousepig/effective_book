package hp.effective.effective_book.ch01;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class PlanningPokerTest {

	@Test
	void rejectNullInput() {
		assertThatThrownBy(
			() -> new PlanningPoker().identifyExtremes(null)
		).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void rejectEmptyList() {
		assertThatThrownBy(() -> {
			List<Estimate> emptyList = Collections.emptyList();
			new PlanningPoker().identifyExtremes(emptyList);
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void rejectSingleEstimate() {
		assertThatThrownBy(() -> {
			List<Estimate> list = Arrays.asList(new Estimate("Eleanor", 1));
			new PlanningPoker().identifyExtremes(list);
		}).isInstanceOf(IllegalArgumentException.class);
	}
}
