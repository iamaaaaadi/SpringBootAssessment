package com.springboot.training.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AssessmentExceptionHandler {
	
	@ExceptionHandler(value = { AssessmentException.class})
	public ResponseEntity<Object> handleAssessmentException
	(AssessmentException assessmentException) {
		return ResponseEntity.status
	(assessmentException.getHttpStatus()).body(assessmentException.getMessage());

	}

}
