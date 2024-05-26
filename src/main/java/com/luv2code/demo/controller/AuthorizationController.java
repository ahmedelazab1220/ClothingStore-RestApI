package com.luv2code.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.demo.dto.response.UserDto;
import com.luv2code.demo.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v2/auth")
@AllArgsConstructor
public class AuthorizationController {

	private final UserService userService;

	@GetMapping("/users/role")
	@PreAuthorize("hasRole('MANAGER')")
	public List<UserDto> findAllUsersByRole(@RequestParam(value = "name") String role) {

		return userService.findAllByRoleName(role);

	}

	@DeleteMapping("")
	@PreAuthorize("hasRole('MANAGER')")
	public ResponseEntity<?> deleteUser(@RequestParam int theId) {

		userService.deleteUserById(theId);

		return ResponseEntity.ok("User deleted successfully");

	}

}
