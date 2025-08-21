package com.sets.RegisterLoginService.model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterDTO {

	@NotBlank(message = "username should not be blank")
	@Size(min = 5,max = 15,message = "username should contain min 5/max 15 characters")
	@Pattern(regexp = "^[a-zA-Z]+$",message = "username must contain only alphabet")
	private String username;
	
	@NotBlank(message = "passward should not be blank")
	@Size(min = 6,max = 10,message = "password should contain min 6/max 10 characters")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&]).{6,10}$",message = "Password must be 6-10 characters, include upper, lower, digit, and special character")
	@Column(length = 100)
	private String password;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;


    @Enumerated(EnumType.STRING)
	private Role role;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
