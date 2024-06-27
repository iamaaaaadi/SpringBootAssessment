package com.springboot.training.request;

import javax.validation.constraints.NotEmpty;

public class VerifyOtpDto {

	@NotEmpty(message = "Please enter a valid username")
	private String username;

	@NotEmpty(message = "Please enter a valid OTP")
	private String otp;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

}