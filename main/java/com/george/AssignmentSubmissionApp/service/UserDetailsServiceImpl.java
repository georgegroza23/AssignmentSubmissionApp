package com.george.AssignmentSubmissionApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.george.AssignmentSubmissionApp.entities.User;
import com.george.AssignmentSubmissionApp.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;
	
	public UserDetailsServiceImpl() {
		//default constructor
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepo.findByUsername(username);
				            
		return userOptional.orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
	}
	
}
