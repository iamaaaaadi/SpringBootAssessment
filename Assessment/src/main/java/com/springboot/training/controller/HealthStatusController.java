package com.springboot.training.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")

public class HealthStatusController {

// For Testing API 
	@GetMapping("health-status")

	// TODO Auto-generated constructor stub

	public ResponseEntity<String> healthCheck() {
		return ResponseEntity.status(HttpStatus.OK).body("Health Status is Good");
	}
}
