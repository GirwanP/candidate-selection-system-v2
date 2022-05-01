package com.csscv.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.csscv.auth.entities.QualificationEntry;
import com.csscv.auth.entities.QualificationLink;
import com.csscv.auth.entities.SelectionProcess;

public interface QualificationLinkRepository extends JpaRepository<QualificationLink, Long>{

//	@Query("select c.qualifications from Candidate c where c.user=?1 ")
	
	@Query("select c from QualificationLink c where c.selectionProcess.id=?1 ")
	public List<QualificationLink> getQualificationLinkListForSProcess(Long sprocessid);
	
	
	@Deprecated
	@Query("select c.selectionProcess from QualificationLink c where c.selectionProcess.id=?1 ")
	public SelectionProcess getSProcess(Long sprocessid);
	@Query("select c.qulification from QualificationLink c where c.selectionProcess.id=?1 ")
	@Deprecated
	public QualificationEntry getQualificationSProcess(Long sprocessid);
	
	
	
	@Query("select c,c.selectionProcess,c.qulification from QualificationLink c where c.id=?1 ")
	public Object[] getTestData(Long qlinkid);
}
