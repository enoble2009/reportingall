package com.magnus.reportingall.domain.users;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_sessions")
public class UserSession {
	
	@Id
	@Column(name = "token")
	private String token;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "login_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginDate;

	public UserSession() {
		super();
	}

	public UserSession(User u, String token) {
		this.token = token;
		this.user = u;
		this.loginDate = new Date();
	}

	@Override
	public String toString() {
		return "UserSession [token=" + token + ", loginDate=" + loginDate + "]";
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
}
