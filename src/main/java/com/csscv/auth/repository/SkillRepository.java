package com.csscv.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csscv.auth.entities.Skill;
import com.csscv.auth.entities.User;

public interface SkillRepository extends JpaRepository<Skill, Long> {
//    User findByUsername(String username);
}
