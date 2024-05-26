package com.luv2code.demo.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.luv2code.demo.dto.mapper.SystemMapper;
import com.luv2code.demo.dto.request.LoginRequest;
import com.luv2code.demo.dto.request.RegisterRequest;
import com.luv2code.demo.dto.response.JwtResponse;
import com.luv2code.demo.entity.User;
import com.luv2code.demo.repo.RoleRepository;
import com.luv2code.demo.repo.UserRepository;
import com.luv2code.demo.service.AuthenticationService;
import com.luv2code.demo.service.JwtService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final SystemMapper mapper;
	private final JwtService jwtService;

	@Override
	public JwtResponse login(LoginRequest loginRequest) {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		String accessToken = jwtService.generateToken(loginRequest.getEmail());

		return JwtResponse.builder().access_token(accessToken).build();
	}

	@Override
	public User signUp(RegisterRequest registerRequest) {
		User user = mapper.registerRequstToUser(registerRequest);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user.setRole(roleRepository.findByroleName(registerRequest.getRole()));

		return userRepository.save(user);
	}

}
