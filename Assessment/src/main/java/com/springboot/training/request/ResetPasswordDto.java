package com.springboot.training.request;

public class ResetPasswordDto {
	private String username;
	private String otp;
	private String newPassword;

	public String getUsername() {
		return username;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
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
