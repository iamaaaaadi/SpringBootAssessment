package com.springboot.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.training.exception.UserAlreadyExistsException;
import com.springboot.training.request.UserRegistrationDto;
import com.springboot.training.service.RegistrationService;

@RequestMapping("/api/v1/")
@RestController
public class RegistrationController {

	private final RegistrationService registrationService;

	@Autowired
	public RegistrationController(RegistrationService registrationService) {
		super();
		this.registrationService = registrationService;
	}

	@PostMapping("register")
	public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
		try {
			String response = registrationService.registerUser(userRegistrationDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (UserAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while processing the request.");
		}
	}

}
