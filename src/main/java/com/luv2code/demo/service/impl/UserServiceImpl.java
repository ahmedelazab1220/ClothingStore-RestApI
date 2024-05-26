package com.luv2code.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.luv2code.demo.dto.mapper.SystemMapper;
import com.luv2code.demo.dto.response.UserDto;
import com.luv2code.demo.entity.User;
import com.luv2code.demo.exc.custom.NotFoundException;
import com.luv2code.demo.exc.custom.NotFoundTypeException;
import com.luv2code.demo.repo.UserRepository;
import com.luv2code.demo.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final SystemMapper mapper;

	@Override
	public List<UserDto> findAllByRoleName(String roleName) {

		List<UserDto> users = userRepository.findAllByRoleName(roleName).stream().map(mapper::userToUserDto).toList();

		return users;
	}

	@Override
	public void deleteUserById(long theId) {

		Optional<User> user = userRepository.findById(theId);

		if (user.isEmpty()) {
			throw new NotFoundException(NotFoundTypeException.User + " Not Found!");
		}

		userRepository.deleteById(theId);
	}

}
