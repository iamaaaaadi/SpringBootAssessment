package com.springboot.training.request;

import javax.validation.constraints.NotEmpty;

public class ResetPasswordDto {

	@NotEmpty(message = "Please enter a valid username")
	private String username;

	@NotEmpty(message = "Please enter new password")
	private String newPassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
