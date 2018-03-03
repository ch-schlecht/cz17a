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

	/**
	 * called after payout, fills the Jackpot with the default value
	 */
	public void fill() {
		amount = initialPoints;
	}
	
	/**
	 * empties the jackpot
	 */
	public void clear() {
		this.isActive = false;
		amount = 0;
	}
	
	/**
	 * called after payout, resets chance of payout, clears and fills jackpot with default
	 */
	public void payedOut() {
		clear();
		fill();
		payoutChance = initialPayoutChance;
		payoutCounter++;
	}
	/**
	 * increase payout chance by a certain value
	 * @param value
	 */
	public void increasePayoutChance(int value) {
		payoutChance += value;
	}
	
	/**
	 * randomly determines if a jackpot is going to be activated
	 */
	public void randomActivation() {
		int payoutTreshhold = 100 - payoutChance;
		int random; 
	    random = (int)(Math.random() * 100) + 1; 
	    if(random >= payoutTreshhold) {
	    	isActive = true;
	    }
	}
	
	public void addPoints(int points) {
		amount += points;
	}
}
