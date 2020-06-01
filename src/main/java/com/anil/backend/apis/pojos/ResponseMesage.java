package com.anil.backend.apis.pojos;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ResponseMesage extends AbstractPojo {

	private String applicationCode;
	private Object response;
	
	public static ResponseMesage of(Object response, String applicationCode) {
		Objects.requireNonNull(response);
		return  new ResponseMesage(applicationCode,response);
	}
}
