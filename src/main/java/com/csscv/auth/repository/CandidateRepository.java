package com.csscv.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.csscv.auth.entities.Candidate;
import com.csscv.auth.entities.QualificationEntry;
import com.csscv.auth.entities.Skill;
import com.csscv.auth.entities.User;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{
	
//	@Query("select c from Candidate c where c.user=?1 ")
	public Candidate findByUser(User user);
	
	@Query("select c.qualifications from Candidate c where c.user=?1 ")
	public List<QualificationEntry> getUserqualifications(User user);
	
	@Query("select c.skills from Candidate c where c.user=?1 ")
	public List<Skill> getUserSkills(User user);
	
	@Query("select c.user from Candidate c where c.id=?1 ")
	public User getUser(Long candidateId);
	
	
	
}
