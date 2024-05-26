package com.luv2code.demo.service.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luv2code.demo.entity.User;
import com.luv2code.demo.repo.UserRepository;
import com.luv2code.demo.security.SecurityUser;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<User> user = userRepository.findByEmail(username);

		if (!user.isPresent()) {
			throw new UsernameNotFoundException("User Not Found!");
		}

		return user.map(SecurityUser::new).get();
	}

}
