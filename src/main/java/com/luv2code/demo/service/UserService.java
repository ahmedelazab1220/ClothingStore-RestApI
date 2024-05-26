package com.luv2code.demo.service;

import java.util.List;

import com.luv2code.demo.dto.response.UserDto;

public interface UserService {

	List<UserDto> findAllByRoleName(String roleName);

	void deleteUserById(long theId);

}
