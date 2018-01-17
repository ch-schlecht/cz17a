package cz17a.gamification.gameserver;

public class Admin extends User{
	private String first_name;
	private String last_name;
	
	public Admin() {}
	
	public Admin(int id, String mail, String nickname, String password, String first_name, String last_name) {
		super(id, mail, nickname, password);
		this.first_name = first_name;
		this.last_name = last_name;
	}

	//Getters and Setters
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	/**
	 * Method generating the full name
	 * @return the full name of the admin as a String
	 */
	public String name() {
		return this.first_name + " " + this.last_name;
	}

}
