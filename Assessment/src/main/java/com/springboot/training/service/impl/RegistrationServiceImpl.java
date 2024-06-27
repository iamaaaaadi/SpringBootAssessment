package com.springboot.training.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.training.dao.RegisterUserRepository;
import com.springboot.training.entity.Users;
import com.springboot.training.exception.AssessmentException;
import com.springboot.training.request.UserRegistrationDto;
import com.springboot.training.service.RegistrationService;
import com.springboot.training.util.ConstantUtil;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private final RegisterUserRepository registerUserRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public RegistrationServiceImpl(RegisterUserRepository registerUserRepository, ModelMapper modelMapper) {
		this.registerUserRepository = registerUserRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public String registerUser(UserRegistrationDto userRegistrationRequestDto) throws AssessmentException {

		// Checking if username already exists
		Optional<Users> existingUserByUsername = registerUserRepository
				.findByUsername(userRegistrationRequestDto.getUsername());

		if (existingUserByUsername.isPresent()) {
			throw new AssessmentException(HttpStatus.CONFLICT, ConstantUtil.USER_ALREADY_EXIST);
		}

		// Saving user info
		Users user = modelMapper.map(userRegistrationRequestDto, Users.class);
		registerUserRepository.save(user);
		return "User has been created.";
	}

}
