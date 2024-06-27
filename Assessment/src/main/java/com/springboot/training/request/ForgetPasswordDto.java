package com.springboot.training.request;

import javax.validation.constraints.NotEmpty;

public class ForgetPasswordDto {

	@NotEmpty(message = "Please enter a valid username")
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
