package data.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name = "Person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
	@Id
	protected int id;
	protected String mail;
	protected String nickname;
	protected String password;
	protected Calendar lastLogin;
	protected Calendar registration;
	
	public User() {} //default constructor
	
	public User(String mail, String nickname, String password) {
		this.mail = mail;
		this.nickname = nickname;
		this.password = password;
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Calendar getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Calendar lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Calendar getRegistration() {
		return registration;
	}

	public void setRegistration(Calendar registration) {
		this.registration = registration;
	}
	
	

}
