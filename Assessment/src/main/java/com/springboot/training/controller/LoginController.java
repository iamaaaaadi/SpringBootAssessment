package com.springboot.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.training.exception.AssessmentException;
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
	public ResponseEntity<String> loginUser(@RequestBody UserLoginDto userLoginDto) throws AssessmentException {
		String response = loginService.loginUser(userLoginDto);
		return ResponseEntity.ok(response);
	}

	@PostMapping("reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto)
			throws AssessmentException {

		String response = loginService.resetPassword(resetPasswordDto);
		return ResponseEntity.ok(response);
	}

}
