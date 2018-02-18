package game;

public class Jackpot {
	private final int initialPoints = 200;
	/**
	 * Current amount of points
	 */
	private int amount;
	/**
	 * Counts, how many times the Jackpot was payed out
	 */
	private int payoutCounter;
	
	public Jackpot() {
		this.amount = initialPoints;
		this.payoutCounter = 0;
	}
	
	public int getInitialPoints() {
		return initialPoints;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPayoutCounter() {
		return payoutCounter;
	}

	public void setPayoutCounter(int payoutCounter) {
		this.payoutCounter = payoutCounter;
	}
	
	public void fill() {
		amount = initialPoints;
	}
	
	public void clear() {
		amount = 0;
	}
	
	public int payOut() {
		int payOut = new Integer(amount);
		clear();
		fill();
		payoutCounter++;
		return payOut;
	}
}
