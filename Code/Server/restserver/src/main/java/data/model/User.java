package data.model;

import java.util.Calendar;

import javax.persistence.Column;
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
	@Column(name = "last_login")
	protected Calendar lastLogin;
	protected Calendar registration;
	
	/**
	 * default constructor
	 */
	public User() {}
	
	/**
	 * Standard Constructor
	 * @param mail String of mail
	 * @param nickname String of nickname
	 * @param password String of password
	 */
	public User(String mail, String nickname, String password) {
		this.mail = mail;
		this.nickname = nickname;
		this.password = password; //TODO hash it
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
