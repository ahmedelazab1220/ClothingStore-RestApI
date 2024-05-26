package com.luv2code.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.demo.dto.request.LoginRequest;
import com.luv2code.demo.dto.request.RegisterRequest;
import com.luv2code.demo.dto.response.JwtResponse;
import com.luv2code.demo.entity.User;
import com.luv2code.demo.service.AuthenticationService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

	private final AuthenticationService userService;

	@PostMapping("/register")
	public ResponseEntity<User> signUp(@Valid @RequestBody RegisterRequest registerRequest) {
		return ResponseEntity.ok(userService.signUp(registerRequest));
	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(userService.login(loginRequest));
	}

}
