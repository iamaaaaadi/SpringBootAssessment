package com.springboot.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Global Exception Handler to handle all the exception 

@ControllerAdvice
public class AssessmentExceptionHandler {

	@ExceptionHandler(value = { AssessmentException.class })
	public ResponseEntity<Object> handleAssessmentException(AssessmentException assessmentException) {
		return ResponseEntity.status(assessmentException.getHttpStatus()).body(assessmentException.getMessage());

	}

	// For handling runtime exception

	@ExceptionHandler(value = { RuntimeException.class })
	public ResponseEntity<Object> handleRuntimeException(RuntimeException runtimeException) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Some thing is wrong. please try again later");
	}

}
