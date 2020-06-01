package com.anil.backend.constants;

import org.springframework.http.HttpStatus;

public enum ExceptionEnum {
	//FEED exception
	FEED100("The specified user does not exist on the database ",HttpStatus.BAD_REQUEST),
	FEED101("The specified user trying to post does not exist on the database.",HttpStatus.BAD_REQUEST),
	
	// User Exception
	USER100("The specified user does not exist on the database", HttpStatus.BAD_REQUEST);
	private final String message;
	private final  HttpStatus statusCode;
	ExceptionEnum(String message, HttpStatus statusCode) {
		this.message=message;
		this.statusCode=statusCode;
	}
	public String getMessage() {
		return this.message;
	}
	public HttpStatus getStatusCode() {
		return this.statusCode;
	}
}
