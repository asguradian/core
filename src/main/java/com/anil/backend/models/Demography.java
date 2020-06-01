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
public class Demography extends AbstractPojo {
  private Orientation orientation;
  private boolean relationShip;
  
  public static Demography from(Orientation orientation, boolean relationship) {
 	  Objects.requireNonNull(orientation);
      return new Demography(orientation,relationship);
  }
}
