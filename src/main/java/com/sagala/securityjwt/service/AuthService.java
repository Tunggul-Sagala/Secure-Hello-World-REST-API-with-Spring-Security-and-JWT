package com.sagala.securityjwt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sagala.securityjwt.dto.LoginRequest;
import com.sagala.securityjwt.dto.SignupResponse;
import com.sagala.securityjwt.models.User;
import com.sagala.securityjwt.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private JWTService jwtService;

	public ResponseEntity<Object> signup(User user) {
		userRepository.save(user);
		final UserDetails userDetails =userDetailsService.loadUserByUsername(user.getUsername());
		final String jwt =jwtService.generateToken(userDetails);
		return new ResponseEntity<>(new SignupResponse(jwt), HttpStatus.OK);
	}
	
	public ResponseEntity<String> login(LoginRequest loginRequest) throws Exception {
		String username =loginRequest.getUsername();
		Optional<User> optionalUser =userRepository.findByUsername(username);
		User user =optionalUser.orElseThrow(() -> new UsernameNotFoundException("Invalid username and password"));
		user.setEnabled(true);
		userRepository.save(user);
		return new ResponseEntity<>("You are successfully login", HttpStatus.OK);
	}
	
	
}
