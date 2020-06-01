package com.anil.backend.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import com.anil.backend.apis.pojos.AbstractPojo;

import lombok.Data;
@MappedSuperclass
@Data
public abstract class AbstractModel<T extends AbstractModel> extends AbstractPojo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
	 @Override
	  public String toString() {
	    return String.format("Abstract[id=%d]",id);
	  }
	 @PrePersist
	 protected final void prePersist() {
		 this.applyPrePersistChanges();
	 }
	public abstract void applyPrePersistChanges();
}
