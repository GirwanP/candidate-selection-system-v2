package com.csscv.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csscv.auth.entities.SkillLevel;

public interface SkillLevelRepository extends JpaRepository<SkillLevel, Long>{
	
	public SkillLevel findByName(String name);
}
