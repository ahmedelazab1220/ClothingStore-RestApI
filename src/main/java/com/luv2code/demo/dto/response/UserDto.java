package com.luv2code.demo.dto.response;

import com.luv2code.demo.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private String fullName;

	private String email;

	private String password;

	private String phoneNumber;

	private Role role;

}
