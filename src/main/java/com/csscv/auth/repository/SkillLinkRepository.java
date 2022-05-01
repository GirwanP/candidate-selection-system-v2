package com.csscv.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.csscv.auth.entities.QualificationEntry;
import com.csscv.auth.entities.QualificationLink;
import com.csscv.auth.entities.SelectionProcess;
import com.csscv.auth.entities.Skill;
import com.csscv.auth.entities.SkillLink;
import com.csscv.auth.entities.User;

public interface SkillLinkRepository extends JpaRepository<SkillLink, Long> {
//    User findByUsername(String username);
	
	
	@Query("select c from SkillLink c where c.selectionProcess.id=?1 ")
	public List<SkillLink> getSkillLinkListForSProcess(Long sprocessid);
	
	
	@Deprecated
	@Query("select c.selectionProcess from QualificationLink c where c.selectionProcess.id=?1 ")
	public SelectionProcess getSProcess(Long sprocessid);
	@Query("select c.qulification from QualificationLink c where c.selectionProcess.id=?1 ")
	@Deprecated
	public QualificationEntry getQualificationSProcess(Long sprocessid);
	
	
	
	@Query("select c,c.selectionProcess,c.skill from SkillLink c where c.id=?1 ")
	public Object[] getTestData(Long qlinkid);
}
