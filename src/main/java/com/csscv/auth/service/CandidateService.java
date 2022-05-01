package com.csscv.auth.service;

import java.util.List;
import java.util.Optional;

import com.csscv.auth.entities.Candidate;
import com.csscv.auth.entities.QualificationEntry;
import com.csscv.auth.entities.User;

public interface CandidateService {

	boolean saveCandidateInfo(Candidate c);

	boolean addQualification(Long qualificationId);

	void addCandidatePointsDefaultSP(Candidate c);

	void updateCandidatePoints(Candidate c);

	void updateCandidateInfo(Candidate c);

	boolean removeQualification(Long qualificationId);
	

	Candidate getCandidateForuser(User u);

	List<QualificationEntry> getCandidateQualifications(User u);


	List<Candidate> getall();

	Optional<Candidate> getCandidateByid(long id);

	boolean activateCandidateUser(long candidateid);

	boolean disableCandidateUser(long candidateid);

}
