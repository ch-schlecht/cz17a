package cz17a.gamification.gameserver;

import java.util.Calendar;

public class User {
	private int id;
	private String mail;
	private String nickname;
	private String password;
	private Calendar last_login;
	private Calendar registration;
	
	public User() {} //default constructor
	
	public User(int id, String mail, String nickname, String password) {
		this.id = id;
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

	public Calendar getLast_login() {
		return last_login;
	}

	public void setLast_login(Calendar last_login) {
		this.last_login = last_login;
	}

	public Calendar getRegistration() {
		return registration;
	}

	public void setRegistration(Calendar registration) {
		this.registration = registration;
	}
	
	

}
