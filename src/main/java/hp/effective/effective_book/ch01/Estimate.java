package hp.effective.effective_book.ch01;

public class Estimate {
	private int estimate;
	private String developer;

	public Estimate(String developer, int estimate) {
		this.developer = developer;
		this.estimate = estimate;
	}

	public int getEstimate() {
		return estimate;
	}

	public String getDeveloper() {
		return developer;
	}
}
