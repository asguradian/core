package com.anil.backend.models;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
  @JsonIgnore
  @JoinColumn(name="posted_by",nullable=false)
  @ManyToOne(fetch=FetchType.LAZY)
  private User user;
  
  public static Feed by(User user, String discription, Long documentId) {
	  Objects.requireNonNull(user);
	  Objects.requireNonNull(discription);
	  Objects.requireNonNull(documentId);
	  Feed feed=new Feed();
	  feed.setUser(user);
	  feed.setDiscription(discription);
	  feed.setDocumentId(documentId);
      return feed;
  }

  @Override
  public void applyPrePersistChanges() {
   this.createdDate=new Date();
   this.visible=true;
}
}
