package com.csscv.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.csscv.auth.entities.SelectionProcess;
import com.csscv.auth.entities.User;

public interface SelectionProcessRepository extends JpaRepository<SelectionProcess, Long> {
		
	public SelectionProcess findByName(String name);
	
	public SelectionProcess findByUniqueId(String uniqueId);
	
	//**
	public Page<SelectionProcess> findAllByCreator(User creator,Pageable pageable);
	
	@Query("select c.creator from SelectionProcess c where c.id=?1 ")
	public Optional<User> getCreaterForspid(Long spid);
	
	
}
