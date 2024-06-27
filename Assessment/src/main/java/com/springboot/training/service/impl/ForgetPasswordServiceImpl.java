package com.springboot.training.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.training.dao.UserRepository;
import com.springboot.training.entity.Users;
import com.springboot.training.exception.AssessmentException;
import com.springboot.training.request.ForgetPasswordDto;
import com.springboot.training.request.VerifyOtpDto;
import com.springboot.training.service.ForgetPasswordService;
import com.springboot.training.util.ConstantUtil;

@Service
public class ForgetPasswordServiceImpl implements ForgetPasswordService {

	private final UserRepository userRepository;

	@Autowired
	public ForgetPasswordServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public String generateOtp(ForgetPasswordDto forgetPasswordDto) throws AssessmentException {
		// TODO Auto-generated method stub

		Optional<Users> optionalUser = userRepository.findByUsername(forgetPasswordDto.getUsername());
		if (optionalUser.isEmpty()) {
			throw new AssessmentException(HttpStatus.NOT_FOUND, ConstantUtil.USER_NOT_EXIST);
		}

		Users user = optionalUser.get();
		String otp = String.valueOf(new Random().nextInt(900000) + 100000);
		user.setOtp(otp);
		user.setOtpGeneratedTime(LocalDateTime.now());
		user.setIsOtpVerified(false);
		userRepository.save(user);

		return otp;
	}

	@Override
	public String verifyOtp(VerifyOtpDto verifyOtpDto) throws AssessmentException {
		// TODO Auto-generated method stub

		Optional<Users> optionalUser = userRepository.findByUsername(verifyOtpDto.getUsername());
		if (optionalUser.isEmpty()) {
			throw new AssessmentException(HttpStatus.NOT_FOUND, ConstantUtil.USER_NOT_EXIST);
		}

		Users user = optionalUser.get();

		if (!user.getOtp().equals(verifyOtpDto.getOtp())) {
			throw new AssessmentException(HttpStatus.BAD_REQUEST, ConstantUtil.OTP_MISMATCH);
		}

		if (!user.getOtpGeneratedTime().plusMinutes(5).isAfter(LocalDateTime.now())) {
            throw new AssessmentException(HttpStatus.BAD_REQUEST, ConstantUtil.OTP_EXPIRED); 
		}
		
		user.setIsOtpVerified(true); 
		userRepository.save(user);
		return "OTP is Verified"; 
		
	}

}
