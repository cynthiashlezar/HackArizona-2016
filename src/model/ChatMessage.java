package model;

import java.util.Date;

public class ChatMessage {
	
	private String message, username;
	private Date timestamp;
	
	public ChatMessage(String message, String username) {
		timestamp = new Date(); //this stores timestamp to a Date object storing the exact time (down to the millisecond)
		this.message = message;
		this.username = username;
	}
	
	public String message() { //this returns the message as it appears in the chat! use this.
		return username + ": " + message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getUsername() {
		return username;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
}
