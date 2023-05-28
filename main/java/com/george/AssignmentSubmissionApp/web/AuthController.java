package com.george.AssignmentSubmissionApp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.george.AssignmentSubmissionApp.dot.AuthCredentialsRequest;
import com.george.AssignmentSubmissionApp.entities.User;
import com.george.AssignmentSubmissionApp.utility.JWTUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JWTUtil jwtUtil;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthCredentialsRequest request) {
		try {
			Authentication authenticate = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			
			User user = (User) authenticate.getPrincipal();
			user.setPassword(null);
			
			return ResponseEntity.ok()
								 .header(HttpHeaders.AUTHORIZATION, jwtUtil.generateToken(user))
								 .body(user);
		} catch(BadCredentialsException bex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
