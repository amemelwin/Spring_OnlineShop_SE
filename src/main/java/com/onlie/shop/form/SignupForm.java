package com.onlie.shop.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignupForm {
	@NotEmpty(message="Email is required")
	private String email;
	@NotEmpty(message="Password is required")
	private String password;
	@NotEmpty(message="Confirm Password is required")
	private String confirmPassword;
	@NotEmpty(message="Address is required")
	private String address;
	

}
