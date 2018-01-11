package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Test {
	@Id 
	public int id;
	
	public Test(int id) {
		this.id = id;
	}
}
