package com.csscv.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.csscv.auth.entities.Recruiter;
import com.csscv.auth.entities.User;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long>{
	
//	public Recruiter findByName(String name);

	@Query("select c.user from Recruiter c where c.id=?1 ")
	public User getUser(Long candidateId);
}
