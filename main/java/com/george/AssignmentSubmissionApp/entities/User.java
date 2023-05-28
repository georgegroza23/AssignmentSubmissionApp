package com.george.AssignmentSubmissionApp.entities;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name = "users")
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate cohortStartDate;
	private String username;
	private String password;
	
	public Long getId() {
		return id;
	}
	
	public LocalDate getCohortStartDate() {
		return cohortStartDate;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setCohortStartDate(LocalDate cohortStartDate) {
		this.cohortStartDate = cohortStartDate;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new Authority("ROLE_STUDENT"));
		
		return roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
