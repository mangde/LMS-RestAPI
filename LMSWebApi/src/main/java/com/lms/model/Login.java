package com.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="admin_details")
public class Login {
	
	@Column(name="username")
	private String userName;
	
	@Column(name="password")
	private String password;

	

	public Login(String username, String password) {
		super();
		this.userName = username;
		this.password = password;
	}

	public Login() {
	}
	
	public String getUserName() {
		return this.userName;
	}
	public String getPassword() {
		return this.password;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
