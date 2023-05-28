package com.george.AssignmentSubmissionApp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity 
public class Assignment {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String status;
	private String gitHubUrl;
	private String branch;
	private String codeReviewUrl;
	@ManyToOne(optional = false)
	private User user;
	// TO DO: create private User asignedTo;
	
	public Long getId() {
		return id;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getGitHubUrl() {
		return gitHubUrl;
	}
	
	public String getBranch() {
		return branch;
	}
	
	public String getCodeReviewUrl() {
		return codeReviewUrl;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setGitHubUrl(String gitHubUrl) {
		this.gitHubUrl = gitHubUrl;
	}
	
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public void setCodeReviewUrl(String codeReviewUrl) {
		this.codeReviewUrl = codeReviewUrl;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
