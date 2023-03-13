package hp.effective.effective_book.ch01;

import java.util.Arrays;
import java.util.List;

/**
 * 메서드: identifyExtremes
 * 	- 이 메서드는 개발자와 추정값으로 이루어진 추정(Estimate) 목록을 받아서 양극단의 추정값을 제시한 두 개발자를 반환해야 한다.
 * 입력: Estimate 목록
 * 	- 각 항목은 개발자의 이름과 추정값을 포함한다.
 * 출력: String 목록
 * 	- 가장 낮은 추정값과 가장 높은 추정값을 제시한 개발자의 이름을 포함해야 한다.
 * */
public class PlanningPoker {

	public List<String> identifyExtremes(List<Estimate> estimates) {

		Estimate lowesEstimate = null;
		Estimate highesEstimate = null;

		for (Estimate estimate : estimates) {
			if (highesEstimate == null || estimate.getEstimate() > highesEstimate.getEstimate()) {
				highesEstimate = estimate;
			}
			// 1. else if를 if로 바꿔서 버그를 수정
			if (lowesEstimate == null || estimate.getEstimate() < lowesEstimate.getEstimate()) {
				lowesEstimate = estimate;
			}
		}

		return Arrays.asList(
			lowesEstimate.getDeveloper(),
			highesEstimate.getDeveloper()
		);
	}
}

/**
 * 플래닝 포커
 * 플래닝 포커(planning poker)는 인기 있는 애자일 추정 기법이다.
 * 플래닝 포커 세션에서 개발자들은 특정 백로그를 개발할 때 드는 노력이 얼마나 될지 예측한다.
 * 팀원들은 개발할 기능에 대해 토론을 한 후, 각 개발자는 자신의 예측치를 1부터 팀에서 정한 특정 숫자까지의 값을 제시한다.
 * 숫자가 높을수록 기능 개발에 더 많은 노력이 든다는 뜻이다.
 * e.g. 누군가 어떤 기능 개발에 대해 8점을 제시했고 다른 이가 2점을 제시했다면 4배의 노력이 더 든다는 뜻이다.
 *
 * 가장 적은 예측을 한 사람과 가장 많은 예측을 한 사람이 자신의 관점을 팀원들에게 설명한다.
 * 팀원들은 다시 토론을 해서 기능 개발에 얼마나 노력이 들지 합의에 이를 때까지 플래닝 포커를 반복한다.
 * */
