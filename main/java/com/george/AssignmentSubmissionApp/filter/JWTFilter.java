package com.george.AssignmentSubmissionApp.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.george.AssignmentSubmissionApp.repository.UserRepository;
import com.george.AssignmentSubmissionApp.utility.JWTUtil;

@Component
public class JWTFilter extends OncePerRequestFilter {
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	JWTUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		//Get authorization header and validate.
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(!StringUtils.hasText(header) || StringUtils.hasText(header) && !header.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return;
		}
		
		// Authorization -> [Bearer] [token(a121sdasfw23asd1as3dw5dasd23]
		final String token = header.split("")[1].trim(); // this will split the authoriz. into an array of 2 values like above and
														//then it will retrieve the value from index 1.
		
		//Get user identity and set it on the spring security context
		UserDetails userDetails = userRepo
				.findByUsername(jwtUtil.getUsernameFromToken(token))
				.orElse(null);
		
		//Get jwt token and validate
		if(!jwtUtil.validateToken(token, userDetails)) {
			chain.doFilter(request, response);
			return;
		}
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				userDetails, null,
				userDetails == null? List.of() : userDetails.getAuthorities());
		
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
	
	
}
