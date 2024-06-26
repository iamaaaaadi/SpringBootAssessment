package com.springboot.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.training.exception.AccountLockedException;
import com.springboot.training.exception.InvalidCredentialsException;
import com.springboot.training.request.ResetPasswordDto;
import com.springboot.training.request.UserLoginDto;
import com.springboot.training.service.LoginService;

@RequestMapping("/api/v1/")
@RestController
public class LoginController {

	private final LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping("login")
	public ResponseEntity<String> loginUser(@RequestBody UserLoginDto userLoginDto) {
		try {
			String response = loginService.loginUser(userLoginDto);
			return ResponseEntity.ok(response);
		} catch (InvalidCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		} catch (AccountLockedException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while processing the request.");
		}
	}

	@PostMapping("reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
		try {
			String response = loginService.resetPassword(resetPasswordDto);
			return ResponseEntity.ok(response);
		} catch (InvalidCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("An error occurred while processing the request.");
		}
	}

}
