package com.sagala.securityjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor	
public class SignupRequest {
	
	private String username;
	private String password;
	private String email;
	
}
