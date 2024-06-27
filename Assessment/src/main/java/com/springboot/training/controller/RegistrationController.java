package com.springboot.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.training.exception.AssessmentException;
import com.springboot.training.request.UserRegistrationDto;
import com.springboot.training.service.RegistrationService;

import jakarta.validation.Valid;

@RequestMapping("/api/v1/")
@RestController
public class RegistrationController {

	private final RegistrationService registrationService;

	@Autowired
	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@PostMapping("register")
	public ResponseEntity<String> registerUser( @Valid	 @RequestBody UserRegistrationDto userRegistrationDto)
			throws AssessmentException {
		
		String response = registrationService.registerUser(userRegistrationDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
