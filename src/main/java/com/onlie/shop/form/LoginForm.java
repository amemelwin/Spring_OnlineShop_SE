package com.onlie.shop.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {
	@NotEmpty(message="Email is required")
	private String email;
	@NotEmpty(message="Password is required")
	private String password;
	

}
