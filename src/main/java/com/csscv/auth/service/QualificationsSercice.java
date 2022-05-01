package com.csscv.auth.service;

import java.util.List;

import com.csscv.auth.dto.QualificationEntryDto;
import com.csscv.auth.dto.QualificationLinkDto;
import com.csscv.auth.entities.Candidate;
import com.csscv.auth.entities.QualificationEntry;
import com.csscv.auth.entities.User;

public interface QualificationsSercice {


//	boolean addQualification(Long qualificationId);


//	boolean removeQualification(Long qualificationId);

	List<QualificationEntry> getCandidateQualifications(User u);


	List<QualificationEntry> getAllQualifications();


	List<QualificationLinkDto> getQualificationLinksForSP(long selectionProcessid);


	boolean addUserQualification(Long qid);


	boolean removeUserQualification(Long qid);


	boolean addMyQualification(Long qualificationId);

	boolean removeMyQualification(Long qualificationId);


//	boolean saveQualification(QualificationEntry q);
//

	boolean saveQualificationLinkForSP(QualificationLinkDto dto);


	boolean saveQualification(QualificationEntryDto qualification);


	boolean removeQualificationLinkForSP(Long qlinkId);


	boolean deleteQualification(Long qid);

}
