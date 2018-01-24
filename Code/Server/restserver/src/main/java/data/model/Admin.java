package data.model;

import javax.persistence.Entity;

@Entity
public class Admin extends User {
	private String firstName;
	private String lastName;
	
	public Admin() {}
	
	public Admin(int id, String mail, String nickname, String password, String firstName, String lastName) {
		this.mail = mail;
		this.nickname = nickname;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	//Getters and Setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Method generating the full name
	 * @return the full name of the admin as a String
	 */
	public String name() {
		return this.firstName + " " + this.lastName;
	}

}
