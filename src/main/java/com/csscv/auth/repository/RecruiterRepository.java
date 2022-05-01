package com.csscv.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.csscv.auth.entities.Recruiter;
import com.csscv.auth.entities.User;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long>{
	

	@Query("select c.user from Recruiter c where c.id=?1 ")
	public User getUser(Long candidateId);
	
	@Query("select c from Recruiter c where c.user.id=?1 ")
	public Optional<Recruiter> getRecruiterByUserId(Long userId);
}
