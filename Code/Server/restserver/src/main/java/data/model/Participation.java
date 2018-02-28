package data.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "Quiz_Participation")
public class Participation implements Serializable, Comparable<Participation> {
	@Id
	@ManyToOne
	private Player player;
	@Id
	@ManyToOne
	private Round round;
	private int rank;
	private int score;

	/**
	 * Default Constructor
	 */
	public Participation() {
	}

	/**
	 * Standard Constructor
	 * 
	 * @param player
	 *            a Player Object
	 * @param round
	 *            a Round Object
	 */
	public Participation(Player player, Round round) {
		this.player = player;
		this.round = round;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int compareTo(Participation o) {
		Participation p = (Participation) o;
		if (score == p.getScore()) {
			if (player.getId() == p.getPlayer().getId()) {
				return 0;
			} else if (player.getId() < p.getPlayer().getId()) {
				return -1;
			} else {
				return 1;
			}
		} else if (score < p.getScore()) {
			return -1;
		} else {
			return 1;
		}
	}
}
