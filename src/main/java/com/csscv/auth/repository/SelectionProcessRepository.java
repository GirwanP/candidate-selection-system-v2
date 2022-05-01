package com.csscv.auth.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.csscv.auth.entities.SelectionProcess;
import com.csscv.auth.entities.User;

public interface SelectionProcessRepository extends JpaRepository<SelectionProcess, Long> {
		
	public SelectionProcess findByName(String name);
	
	public SelectionProcess findByUniqueId(String uniqueId);
	
	//**
	public Page<SelectionProcess> findAllByCreator(User creator,Pageable pageable);
	
}
