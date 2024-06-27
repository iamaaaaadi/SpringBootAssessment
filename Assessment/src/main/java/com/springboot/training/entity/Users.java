package com.springboot.training.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

// Users Table is created with all the fields

@Entity
@Table(name = "users")

public class Users implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7574441416078891070L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@NotEmpty(message = "Please enter a valid username")
	@Column(nullable = false, unique = true)
	private String username;

	@NotEmpty(message = "Please enter a password")
	@Column (nullable = false)
	private String password;

	@NotEmpty(message = "Please enter a valid email")
	@Column(nullable = false, unique = true)
	private String email;

	@Column
	private boolean blocked = false;

	@Column
	private LocalDateTime blockTime;

	@Column
	private int loginAttempts = 0;

	@Column
	private LocalDateTime lastLoginAttempt;

	
	private String otp;

	@Column
	private LocalDateTime otpGeneratedTime;
	
    @Column
    private boolean isOtpVerified = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public LocalDateTime getLastLoginAttempt() {
		return lastLoginAttempt;
	}

	public void setLastLoginAttempt(LocalDateTime lastLoginAttempt) {
		this.lastLoginAttempt = lastLoginAttempt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public LocalDateTime getBlockTime() {
		return blockTime;
	}

	public void setBlockTime(LocalDateTime blockTime) {
		this.blockTime = blockTime;
	}
	
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getOtpGeneratedTime() {
		return otpGeneratedTime;
	}

	public void setOtpGeneratedTime(LocalDateTime otpGeneratedTime) {
		this.otpGeneratedTime = otpGeneratedTime;
	}

	public boolean getisOtpVerified() {
		return isOtpVerified;
	}

	public void setIsOtpVerified(boolean isOtpVerified) {
		this.isOtpVerified = isOtpVerified;
	}


}