package com.csscv.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csscv.auth.entities.Candidate;
import com.csscv.auth.entities.QualificationEntry;

public interface QualificationEntryRepository extends JpaRepository<QualificationEntry, Long>{

}
