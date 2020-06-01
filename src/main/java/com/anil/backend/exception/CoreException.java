package com.anil.backend.exception;

import com.anil.backend.constants.ExceptionEnum;

import lombok.Getter;

@Getter
public class CoreException extends RuntimeException {
	private final ExceptionEnum expCode;
	public  CoreException(String msg,ExceptionEnum expCode) {
		 super(msg);
		 this.expCode=expCode;
	}
}
