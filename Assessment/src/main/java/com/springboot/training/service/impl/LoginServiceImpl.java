package com.springboot.training.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.training.dao.UserRepository;
import com.springboot.training.entity.Users;
import com.springboot.training.exception.AccountLockedException;
import com.springboot.training.exception.InvalidCredentialsException;
import com.springboot.training.request.ResetPasswordDto;
import com.springboot.training.request.UserLoginDto;
import com.springboot.training.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	private final UserRepository userRepository;

	@Autowired
	public LoginServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public String loginUser(UserLoginDto userLoginDto) {
		// TODO Auto-generated method stub

		Optional<Users> optionalUser = userRepository.findByUsername(userLoginDto.getUsername());

		if (optionalUser.isEmpty()) {
			 throw new InvalidCredentialsException("Invalid username or password.");
		}

		Users user = optionalUser.get();
		System.out.println(user);

		if (user.isBlocked()) {
			LocalDateTime blockEndTime = user.getBlockTime().plusHours(24);
			if (LocalDateTime.now().isBefore(blockEndTime)) {
				  throw new AccountLockedException("Account is locked. Try again after 24 hours.");
			} else {
				user.setBlocked(false);
				user.setLoginAttempts(0);
				user.setBlockTime(null);
			}
		}

		if (userLoginDto.getPassword().equals(user.getPassword())) {
			user.setLoginAttempts(0);
			user.setLastLoginAttempt(null);
			userRepository.save(user);
			return "Logged in Successfully";
		} else {
			user.setLoginAttempts(user.getLoginAttempts() + 1);
			user.setLastLoginAttempt(LocalDateTime.now());

			if (user.getLoginAttempts() >= 5) {
				user.setBlocked(true);
				user.setBlockTime(LocalDateTime.now());
				userRepository.save(user);
				 throw new AccountLockedException("Account locked due to 5 failed login attempts.Try again after 24 hours.");
			}

			userRepository.save(user);
			  throw new InvalidCredentialsException("Invalid username or password. Attempt " + user.getLoginAttempts() + " of 5.");
		}

	}

	@Override
	public String resetPassword(ResetPasswordDto resetPasswordDto) {
		// TODO Auto-generated method stub
		
		Optional<Users> optionalUser = userRepository.findByUsername(resetPasswordDto.getUsername());

        if (optionalUser.isEmpty()) {
        	   throw new InvalidCredentialsException("Invalid username.");
        }

        Users user = optionalUser.get();

        if (!resetPasswordDto.getOldPassword().equals(user.getPassword())) {
        	  throw new InvalidCredentialsException("Old password is incorrect.");
        }

        user.setPassword(resetPasswordDto.getNewPassword());
        userRepository.save(user);
        return "Password reset successful";
		
	}
}
