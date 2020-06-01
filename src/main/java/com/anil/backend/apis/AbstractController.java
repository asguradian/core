package com.anil.backend.apis;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.anil.backend.apis.pojos.ResponseMesage;
import com.anil.backend.constants.ExceptionEnum;
import com.anil.backend.exception.CoreException;
import com.anil.backend.models.AbstractModel;

public abstract class AbstractController<T extends AbstractModel<T>> {
	protected ResponseEntity<ResponseMesage> map(T obj){
		  Objects.requireNonNull(obj);
		  ResponseMesage message= ResponseMesage.of(obj, null);
		  return new ResponseEntity<>(message,HttpStatus.OK);
		}
	protected ResponseEntity<ResponseMesage> map(List<T> obj){
		  Objects.requireNonNull(obj);
		  ResponseMesage message= ResponseMesage.of(obj, null);
		  return new ResponseEntity<>(message,HttpStatus.OK);
		}
	
	@ExceptionHandler(value= {CoreException.class})
	public ResponseEntity<ResponseMesage> handleException(CoreException exp) {
		Objects.requireNonNull(exp);
		String enhancedMesssage=exp.getMessage()==null?this.defaultExceptionMessage(exp.getExpCode()):exp.getMessage();
		return new ResponseEntity<>(ResponseMesage.of(enhancedMesssage,null),this.exceptionStatusCode(exp.getExpCode()));
	}
	
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<ResponseMesage> handleGenericException(Exception exp) {
		Objects.requireNonNull(exp);
		return new ResponseEntity<>(ResponseMesage.of(exp.getMessage(),null),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	public String defaultExceptionMessage(ExceptionEnum expCode) {
		Objects.requireNonNull(expCode);
		return getMessageMap().getOrDefault(expCode, expCode.getMessage());
	}
	
	public HttpStatus exceptionStatusCode(ExceptionEnum expCode) {
		Objects.requireNonNull(expCode);
		return getExceptionMap().getOrDefault(expCode, expCode.getStatusCode());
	}
	public abstract Map<ExceptionEnum, HttpStatus> getExceptionMap();
	public abstract Map<ExceptionEnum, String> getMessageMap();
}
