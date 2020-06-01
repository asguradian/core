package com.anil.backend.models;

import java.util.Objects;

import javax.persistence.Embeddable;

import com.anil.backend.apis.pojos.AbstractPojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper=true)
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends AbstractPojo {
	private String firstName;
	private String lastName;
	private String email;
	public static UserInfo create(String firstName,String lastName, String email) {
	 Objects.requireNonNull(firstName);
	 Objects.requireNonNull(lastName);
	 Objects.requireNonNull(email);
	 return new UserInfo(firstName,lastName,email);
	}
}
