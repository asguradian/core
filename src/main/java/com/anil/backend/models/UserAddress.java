package com.anil.backend.models;

import java.util.Objects;

import javax.persistence.Embeddable;

import com.anil.backend.apis.pojos.AbstractPojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Embeddable
public class UserAddress extends AbstractPojo {
	private  String country;
    private String state;
    
    public static UserAddress create(String country, String state){
     Objects.requireNonNull(country);
     Objects.requireNonNull(state);
     return new UserAddress(country,state);
    }
}
