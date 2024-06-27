package com.springboot.training.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.training.dao.UserRepository;
import com.springboot.training.entity.Users;
import com.springboot.training.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
	}

	@Override
	public List<Users> getAllUser() {
		// TODO Auto-generated method stub

		// Fetching all the users
		return userRepository.findAll();
	}
}
