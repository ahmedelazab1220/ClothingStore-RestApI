package com.luv2code.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luv2code.demo.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByroleName(String roleName);

}
