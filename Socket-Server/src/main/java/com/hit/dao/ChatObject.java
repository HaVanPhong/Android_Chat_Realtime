package com.hit.dao;

public class ChatObject {
	private String fullName;
	private String message;
	public ChatObject(String fullName, String message) {
		super();
		this.fullName = fullName;
		this.message = message;
	}
	public ChatObject() {
		super();
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
