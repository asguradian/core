package com.anil.backend.exception;

import com.anil.backend.constants.ExceptionEnum;

public class UserNotFoundException extends CoreException {
 public UserNotFoundException(String msg, ExceptionEnum code) {
	 super(msg,code);
 }
}
