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
	private boolean isActive;
	
	public Jackpot() {
		this.amount = initialPoints;
		this.payoutCounter = 0;
		this.isActive = false;
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
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void fill() {
		amount = initialPoints;
	}
	
	public void clear() {
		this.isActive = false;
		amount = 0;
	}
	
	public void payedOut() {
		clear();
		fill();
		payoutCounter++;
	}
}
