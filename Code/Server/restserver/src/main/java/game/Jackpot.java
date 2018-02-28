package game;

public class Jackpot {
	private final int initialPoints = 1000;
	private final int initialPayoutChance = 10;
	/**
	 * Current amount of points
	 */
	private int amount;
	/**
	 * Counts, how many times the Jackpot was payed out
	 */
	private int payoutCounter = 0;
	private boolean isActive = false;
	private int payoutChance;
	
	public Jackpot() {
		this.amount = initialPoints;
		this.payoutChance = initialPayoutChance;
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

	public int getPayoutChance() {
		return payoutChance;
	}

	public void setPayoutChance(int payoutChance) {
		this.payoutChance = payoutChance;
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
		payoutChance = initialPayoutChance;
		payoutCounter++;
	}
	
	public void increasePayoutChance(int value) {
		payoutChance += value;
	}
	
	public void randomActivation() {
		int payoutTreshhold = 100 - payoutChance;
		int random; 
	    random = (int)(Math.random() * 100) + 1; 
	    if(random >= payoutTreshhold) {
	    	isActive = true;
	    }
	}
}
