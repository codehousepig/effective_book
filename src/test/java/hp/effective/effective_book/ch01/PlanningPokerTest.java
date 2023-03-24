package hp.effective.effective_book.ch01;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

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

	@Property // 1. 메서드를 기준의 JUit 테스트 대신 속성 기반 테스트로 선언한다.
	// 2. 프레임워크는 무작위로 생성한 추정 목록을 제공한다.
	// 이 목록은 문자열 'estimates'와 일치하는 함수명에 의해 생성된다.
	void estimatesInAnyOrder(@ForAll("estimates") List<Estimate> estimates) {

		estimates.add(new Estimate("MrLowEstimate", 1)); // 3. 생선된 목록에 양극단의 추정을 포함한다.
		estimates.add(new Estimate("MsHighEstimate", 100)); // 3.
		Collections.shuffle(estimates);

		// 4. 목록을 섞어서 순서가 중요하지 않도록 한다.
		List<String> dev = new PlanningPoker().identifyExtremes(estimates);

		// 5. 추정 목록이 어떻게 구성되어 있든 결과에는 항상 MrLowEstimate과 MsHighEstimate이 포함된다고 단언한다.
		assertThat(dev).containsExactlyInAnyOrder("MrLowEstimate", "MsHighEstimate");
	}

	@Provide // 6. 이 메서드는 속성 기반 테스트를 위해 임의의 추정 목록을 제공한다.
	Arbitrary<List<Estimate>> estimates() {
		// 7. 다섯 글자이면서 소문자로만 이루어진 임의의 이름을 생성한다.
		Arbitrary<String> names = Arbitraries.strings().withCharRange('a', 'z').ofLength(5);

		// 8. 2에서 99까지의 임의의 추정값을 생성한다.
		Arbitrary<Integer> values = Arbitraries.integers().between(2, 99);

		// 9. 이를 조합해서 임의의 추정 객체를 만든다.
		Arbitrary<Estimate> estimates = Combinators.combine(names, values).as((name, value) -> new Estimate(name, value));

		// 10. 최소 1개의 요소를 가지는 (실제로는 목록의 크기에 제한이 없는) 추정 목록을 반환한다.
		return estimates.list().ofMinSize(1);
	}
}
