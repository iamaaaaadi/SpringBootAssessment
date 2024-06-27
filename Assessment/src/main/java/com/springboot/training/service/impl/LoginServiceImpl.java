package com.springboot.training.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.training.dao.UserRepository;
import com.springboot.training.entity.Users;
import com.springboot.training.exception.AssessmentException;
import com.springboot.training.request.ResetPasswordDto;
import com.springboot.training.request.UserLoginDto;
import com.springboot.training.service.LoginService;
import com.springboot.training.util.ConstantUtil;

@Service
public class LoginServiceImpl implements LoginService {

	private final UserRepository userRepository;

	@Autowired
	public LoginServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
	}

	@Override
	public String loginUser(UserLoginDto userLoginDto) throws AssessmentException {
		// TODO Auto-generated method stub

		Optional<Users> optionalUser = userRepository.findByUsername(userLoginDto.getUsername());

		// If User does not exist
		if (optionalUser.isEmpty()) {
			throw new AssessmentException(HttpStatus.NOT_FOUND, ConstantUtil.USER_NOT_EXIST);
		}

		Users user = optionalUser.get();

		// If User is blocked

		if (user.isBlocked()) {
			LocalDateTime blockEndTime = user.getBlockTime().plusHours(24);
			if (LocalDateTime.now().isBefore(blockEndTime)) {
				throw new AssessmentException(HttpStatus.FORBIDDEN, ConstantUtil.ACCOUNT_BLOCKED);
			}
			user.setBlocked(false);
			user.setLoginAttempts(0);
			user.setBlockTime(null);
		}

		if (!userLoginDto.getPassword().equals(user.getPassword())) {

			// Handling Incorrect Password Attempts and Blocking User
			user.setLoginAttempts(user.getLoginAttempts() + 1);
			user.setLastLoginAttempt(LocalDateTime.now());

			if (user.getLoginAttempts() >= 5) {
				user.setBlocked(true);
				user.setBlockTime(LocalDateTime.now());
				userRepository.save(user);
				throw new AssessmentException(HttpStatus.FORBIDDEN, ConstantUtil.ACCOUNT_BLOCKED);
			}

			userRepository.save(user);
			throw new AssessmentException(HttpStatus.UNAUTHORIZED, ConstantUtil.INVALID_CREDENTIALS);

		}

		// If Correct Password
		user.setLoginAttempts(0);
		user.setLastLoginAttempt(null);
		userRepository.save(user);
		return "Logged in Successfully !";

	}

	@Override
	public String resetPassword(ResetPasswordDto resetPasswordDto) throws AssessmentException {
		// TODO Auto-generated method stub

		// Checking if user exist or not
		Optional<Users> optionalUser = userRepository.findByUsername(resetPasswordDto.getUsername());

		// If not exist
		if (optionalUser.isEmpty()) {
			throw new AssessmentException(HttpStatus.NOT_FOUND, ConstantUtil.USER_NOT_EXIST);
		}

		Users user = optionalUser.get();

		// If OTP is not verified
		if (!user.getisOtpVerified()) {
			throw new AssessmentException(HttpStatus.UNAUTHORIZED, ConstantUtil.OTP_NOT_VERIFIED);
		}

		// Set New Password if OTP is verified
		user.setPassword(resetPasswordDto.getNewPassword());
		userRepository.save(user);
		return "Password reset successfully";

	}
}
