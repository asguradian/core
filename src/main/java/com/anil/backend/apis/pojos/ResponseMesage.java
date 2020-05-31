package com.anil.backend.apis.pojos;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@JsonInclude(value=Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMesage {

	private String applicationCode;
	private Object response;
	
	public static ResponseMesage of(Object response, String applicationCode) {
		Objects.requireNonNull(response);
		return  new ResponseMesage(applicationCode,response);
	}
}
