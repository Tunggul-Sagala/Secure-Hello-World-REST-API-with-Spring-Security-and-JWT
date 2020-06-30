package com.sagala.securityjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagala.securityjwt.dto.LoginRequest;
import com.sagala.securityjwt.dto.SignupRequest;
import com.sagala.securityjwt.models.User;
import com.sagala.securityjwt.service.AuthService;

@RestController
@RequestMapping("/api")
public class HelloController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/signup") 
	public ResponseEntity<Object> signup(@RequestBody SignupRequest signupRequest) {
		User user =new User();
		user.setUsername(signupRequest.getUsername());
		user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		user.setEmail(signupRequest.getEmail());
		return authService.signup(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		try {
			return authService.login(loginRequest);
		} catch (Exception e) {
			return new ResponseEntity<>("Invalid username/password", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/hello") 
	public String hello() {
		return "Welcome to Spring security JWT";
	}

}
