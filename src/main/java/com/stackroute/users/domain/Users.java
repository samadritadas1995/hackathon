package com.stackroute.users.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Users {
	
	@Id
	
//	private String userId;
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String userName;
	private String emailID;
	
	@Autowired
	public Users(int id,String userName, String emailID) {
		super();
		this.id= id;
		this.userName = userName;
		this.emailID = emailID;
	}
	
	public Users() {}
	
//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}


}
