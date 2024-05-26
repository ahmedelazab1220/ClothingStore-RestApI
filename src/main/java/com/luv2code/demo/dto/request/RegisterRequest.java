package com.luv2code.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

	private String fullName;

	private String email;

	@NotBlank(message = "Password is mandatory")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;

	private String role;

	private String phoneNumber;

}
