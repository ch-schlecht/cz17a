package cz17a.gamification.restserver.resource.round;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="Round")
public class Round {

	@Id
	int ID;
	@Column(name="start")
	Timestamp start;
	@Column(name="end")
	Timestamp end;
	@Column(name="max_score")
	int max_score;
	@Column(name="winner")
	int winner;
	
	public Round() {
		
	}
	
	public Round(int ID, Timestamp start, Timestamp end, int max_score, int winner) {
		this.ID = ID;
		this.start = start;
		this.end = end;
		this.max_score = max_score;
		this.winner = winner;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public int getMax_score() {
		return max_score;
	}

	public void setMax_score(int max_score) {
		this.max_score = max_score;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}
	
	
}
