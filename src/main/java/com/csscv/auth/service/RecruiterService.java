package com.csscv.auth.service;

import java.util.List;
import java.util.Optional;

import com.csscv.auth.dto.QualificationLinkDto;
import com.csscv.auth.dto.RecruiterDto;
import com.csscv.auth.dto.SelectionProcessDto;
import com.csscv.auth.dto.SkillLinkDto;
import com.csscv.auth.entities.Candidate;
import com.csscv.auth.entities.QualificationEntry;
import com.csscv.auth.entities.Recruiter;
import com.csscv.auth.entities.User;

public interface RecruiterService {

	public boolean registerRecruiter(RecruiterDto recruiter);
	boolean addSkillLink(SkillLinkDto dto);
	boolean addJob(SelectionProcessDto job);
	boolean addQualificationLink(QualificationLinkDto dto);
	List<RecruiterDto> getAllRecruiters();
	boolean disableRecruiterUser(long recruiterid);
	boolean activateRecruiterUser(long recruiterid);
	Optional<Recruiter> getRecruitereByid(long id);
	Optional<Recruiter> getRecruitereByUser(long id);

}
