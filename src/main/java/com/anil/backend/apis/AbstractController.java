package com.anil.backend.apis;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.anil.backend.apis.pojos.ResponseMesage;
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
}
