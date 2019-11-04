package com.tyss.library.management.librarymanagement.dto;

import java.util.List;

public class LibraryResponse {
	private int statusCode;
	private String message;
	private String description;
	private UserInfoDto users;
	public UserInfoDto getUsers() {
		return users;
	}
	public void setUsers(UserInfoDto users) {
		this.users = users;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
