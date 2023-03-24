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

	@Test
	void twoEstimates() {
		List<Estimate> list = Arrays.asList( // 두 개의 추정을 지닌 목록을 정의한다.
			new Estimate("Mauricio", 10),
			new Estimate("Frank", 5)
		);
		// 2. 테스트 대상 메서드인 identifyExtremes
		List<String> devs = new PlanningPoker().identifyExtremes(list);

		// 3. 메서드가 두 개발자를 반환하는지 단언한다.
		assertThat(devs).containsExactlyInAnyOrder("Mauricio", "Frank");
	}

	@Test
	void manyEstimates() {
		List<Estimate> list = Arrays.asList( // 4. 이제 세 개발자의 추정을 지닌 목록을 정의한다.
			new Estimate("Mauricio", 10),
			new Estimate("Arie", 5),
			new Estimate("Frank", 7)
		);
		// 5. 테스트상에서 다시 메서드를 호출한다.
		List<String> devs = new PlanningPoker().identifyExtremes(list);

		// 6. 올바른 개발자 이름인 Mauricio, Arie를 반환하는지 확인한다.
		assertThat(devs).containsExactlyInAnyOrder("Mauricio", "Arie");
	}

}
