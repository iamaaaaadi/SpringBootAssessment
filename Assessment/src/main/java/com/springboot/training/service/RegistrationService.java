package com.springboot.training.service;

import com.springboot.training.exception.AssessmentException;
import com.springboot.training.request.UserRegistrationDto;

public interface RegistrationService {
	String registerUser(UserRegistrationDto userRegistrationRequestDto) throws AssessmentException;
}
