
package com.anil.backend.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user")
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractModel<User> {
	@Column(name="user_name",unique=true)
	private String userName;
	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "firstName", column = @Column(name = "first_name")),
		  @AttributeOverride( name = "lastName", column = @Column(name = "last_name")),
		  @AttributeOverride( name = "email", column = @Column(name = "email", unique=true))
		})
	private UserInfo userInfo;
	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "country", column = @Column(name = "country")),
		  @AttributeOverride( name = "state", column = @Column(name = "country_state"))
		})
	private UserAddress userAddress;
	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "orientation", column = @Column(name = "orientation")),
		  @AttributeOverride( name = "status", column = @Column(name = "relationship_status"))
		})
	private Demography demography;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Feed> feeds;
	@Column(name="active")
	private boolean active;
	@Column(name="created_date")
	private Date createdDate;
	@JsonIgnore   
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
	            name = "followers",
	            joinColumns = {@JoinColumn(name = "user_id")},
	            inverseJoinColumns = {@JoinColumn(name = "follower_id")})
	private Set<User> followers;
	@Override
	public void applyPrePersistChanges() {
		this.active=true;
		this.createdDate=new Date();	
	}
}
