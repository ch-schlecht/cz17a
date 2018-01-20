package cz17a.gamification.restserver.resource.quiz;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="Quiz")
public class Quiz {

	@Id
	private int ID;
	@Column(name="title")
	private String title;
	@Column(name="length")
	private int length;
	@Column(name="min_participants")
	private int min_participants;
	@Column(name="max_participants")
	private int max_partipicipants;
	
	public Quiz() {
		
	}
	
	
	public Quiz(int ID, String title, int length, int min, int max) {
		this.ID = ID;
		this.title = title;
		this.length = length;
		this.min_participants = min;
		this.max_partipicipants = max;
	}
	
	
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getMin_participants() {
		return min_participants;
	}
	public void setMin_participants(int min_participants) {
		this.min_participants = min_participants;
	}
	public int getMax_partipicipants() {
		return max_partipicipants;
	}
	public void setMax_partipicipants(int max_partipicipants) {
		this.max_partipicipants = max_partipicipants;
	}
	
	
	
}
