package com.anil.backend.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="feed")
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class Feed extends AbstractModel<Feed> {
  @Column(name="discription",nullable=false)	
  private String discription;
  
  @Column(name="created_date",nullable=false)	  
  private Date createdDate;
  
  @Column(name="visible",nullable=false)	
  private boolean visible;
  
  /**
   * this id will be the primary key of the id document that holds the feed details
   */
  @Column(name="document_id", unique=true)
  private Long documentId;
  
  @JoinColumn(name="posted_by",nullable=false)
  @ManyToOne(fetch=FetchType.LAZY)
  private User user;

  @Override
  public void applyPrePersistChanges() {
   this.createdDate=new Date();
   this.visible=true;
}
}
