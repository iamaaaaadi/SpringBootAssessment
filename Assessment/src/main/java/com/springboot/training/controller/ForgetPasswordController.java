package com.springboot.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.training.exception.AssessmentException;
import com.springboot.training.request.ForgetPasswordDto;
import com.springboot.training.request.VerifyOtpDto;
import com.springboot.training.service.ForgetPasswordService;


@RequestMapping("/api/v1/")
@RestController
public class ForgetPasswordController {

	private final ForgetPasswordService forgetPasswordService;

	@Autowired
	public ForgetPasswordController(ForgetPasswordService forgetPasswordService) {
		this.forgetPasswordService = forgetPasswordService;
	}

	@PostMapping("generate-otp")
	public ResponseEntity<String> generateOtp(@RequestBody ForgetPasswordDto forgetPassowordDto)
			throws AssessmentException {
		String response = forgetPasswordService.generateOtp(forgetPassowordDto);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("verify-otp")
	public ResponseEntity<String> verifyOtp(@RequestBody VerifyOtpDto verifyOtpDto)
			throws AssessmentException {
		String response = forgetPasswordService.verifyOtp(verifyOtpDto);
		return ResponseEntity.ok(response);
	}

}
