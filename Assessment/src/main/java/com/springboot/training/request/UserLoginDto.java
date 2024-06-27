package com.springboot.training.request;

import javax.validation.constraints.NotEmpty;

public class UserLoginDto {

	@NotEmpty(message = "Please enter a valid username")
	private String username;
	
	@NotEmpty(message = "Please enter a valid password")
	private String password;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
