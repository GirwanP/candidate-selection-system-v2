package com.csscv.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csscv.auth.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	public Role findByName(String name);
}
