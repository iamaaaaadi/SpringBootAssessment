package com.springboot.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.training.entity.Users;
import com.springboot.training.service.UserService;

@RequestMapping("/api/v1/")
@RestController
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("list-all")
	public ResponseEntity<List<Users>> getAllUsers() {
		List<Users> response = userService.getAllUser();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
