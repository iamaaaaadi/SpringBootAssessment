package com.springboot.training.exception;

import org.springframework.http.HttpStatus;

public class AssessmentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 864634054642859667L;

	private HttpStatus httpStatus;

	// Exception Handler

	public AssessmentException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
