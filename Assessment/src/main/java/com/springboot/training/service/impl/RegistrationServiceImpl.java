package com.springboot.training.service.impl;



import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.training.dao.RegisterUserRepository;
import com.springboot.training.entity.Users;
import com.springboot.training.exception.UserAlreadyExistsException;
import com.springboot.training.request.UserRegistrationDto;
import com.springboot.training.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private final RegisterUserRepository registerUserRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public RegistrationServiceImpl(RegisterUserRepository registerUserRepository, ModelMapper modelMapper) {
		super();
		this.registerUserRepository = registerUserRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public String registerUser(UserRegistrationDto userRegistrationRequestDto) {
		
		Optional<Users> existingUserByUsername = registerUserRepository.findByUsername(userRegistrationRequestDto.getUsername());
        Optional<Users> existingUserByEmail = registerUserRepository.findByEmail(userRegistrationRequestDto.getEmail());

        if (existingUserByUsername.isPresent()) {
        	 throw new UserAlreadyExistsException("Username already exists.");
        }

        if (existingUserByEmail.isPresent()) {
        	 throw new UserAlreadyExistsException("Email already exists.");
        }
		
		Users user = modelMapper.map(userRegistrationRequestDto, Users.class);
		registerUserRepository.save(user);
		return "User has been created.";
	}

}
