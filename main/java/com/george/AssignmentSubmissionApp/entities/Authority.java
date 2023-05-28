package com.george.AssignmentSubmissionApp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Authority implements GrantedAuthority {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String authority;
	
	@ManyToOne(optional = false)
	private User user;
	
	public Authority() {
		//default constructor
	}
	
	public Authority(String authority) {
		this.authority = authority;
	}
	
	//Getters
	
	public Long getId() {
		return id;
	}
	
	@Override
	public String getAuthority() {
		return authority;
	}
	
	public User getUser() {
		return user;
	}
	
	//Setters
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
