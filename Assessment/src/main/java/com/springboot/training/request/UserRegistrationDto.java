package com.springboot.training.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserRegistrationDto {
	@NotEmpty(message = "Please enter a valid username")
	private String username;

	@NotEmpty(message = "Please enter a valid email")
	@Email(message = "Email should be valid")
	private String email;

	@NotEmpty(message = "Please enter a valid password")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
