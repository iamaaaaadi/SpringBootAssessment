package com.springboot.training.service;

import com.springboot.training.exception.AssessmentException;
import com.springboot.training.request.ForgetPasswordDto;
import com.springboot.training.request.VerifyOtpDto;

public interface ForgetPasswordService {
   
	String generateOtp(ForgetPasswordDto forgetPasswordDto) throws AssessmentException;	
	String verifyOtp(VerifyOtpDto verifyOtpDto) throws AssessmentException; 
}
