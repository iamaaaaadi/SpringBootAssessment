package com.springboot.training.service;

import com.springboot.training.exception.AssessmentException;
import com.springboot.training.request.ResetPasswordDto;
import com.springboot.training.request.UserLoginDto;

public interface LoginService {
	String loginUser(UserLoginDto userLoginDto) throws AssessmentException;
	String resetPassword(ResetPasswordDto resetPasswordDto) throws AssessmentException;

}
