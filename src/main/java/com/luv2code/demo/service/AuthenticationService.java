package com.luv2code.demo.service;

import com.luv2code.demo.dto.request.LoginRequest;
import com.luv2code.demo.dto.request.RegisterRequest;
import com.luv2code.demo.dto.response.JwtResponse;
import com.luv2code.demo.entity.User;

public interface AuthenticationService {

	JwtResponse login(LoginRequest loginRequest);

	User signUp(RegisterRequest registerRequest);

}
