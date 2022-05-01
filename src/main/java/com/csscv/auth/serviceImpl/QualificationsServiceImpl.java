package com.csscv.auth.serviceImpl;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.csscv.auth.dto.QualificationEntryDto;
import com.csscv.auth.dto.QualificationLinkDto;
import com.csscv.auth.entities.Candidate;
import com.csscv.auth.entities.QualificationEntry;
import com.csscv.auth.entities.QualificationLink;
import com.csscv.auth.entities.QualificationType;
import com.csscv.auth.entities.SelectionProcess;
import com.csscv.auth.entities.SelectionProcessLink;
import com.csscv.auth.entities.User;
import com.csscv.auth.repository.CandidateRepository;
import com.csscv.auth.repository.QualificationEntryRepository;
import com.csscv.auth.repository.QualificationLinkRepository;
import com.csscv.auth.repository.QualificationTypeRepository;
import com.csscv.auth.repository.SelectionProcessLinkRepository;
import com.csscv.auth.repository.SelectionProcessRepository;
import com.csscv.auth.service.CandidateService;
import com.csscv.auth.service.PointsService;
import com.csscv.auth.service.QualificationsSercice;
import com.csscv.auth.service.SecurityService;

@Service
public class QualificationsServiceImpl implements QualificationsSercice {
	
	
	@Autowired
	CandidateRepository candidateRepository;
	@Autowired
	SelectionProcessRepository selectionProcessRepository;
	@Autowired
	SelectionProcessLinkRepository selectionProcessLinkRepository;
	@Autowired
	QualificationEntryRepository qualificationEntryRepository;
	
	@Autowired
	PointsService pointsService; 
	
	@Autowired
	SecurityService securityService;
	
	@Autowired
	CandidateService  candidateservice;	
	
	@Autowired
	QualificationLinkRepository qualificationLinkRepository;
	
	@Autowired
	QualificationTypeRepository qualificationTypeRepository;
	
	@Override
	public boolean saveQualification(QualificationEntryDto qualification) {
		
		Period p=Period.of(qualification.getYears(), qualification.getMonths(), qualification.getDays());
    	
		QualificationEntry q=new QualificationEntry();
		
		QualificationType t=qualificationTypeRepository.getOne(qualification.getTypeid());
		q.setType(t);
		q.setLength(p);
		q.setFieldOfStudy(qualification.getFieldOfStdy());
		q.setName(qualification.getName());
		
		
		qualificationEntryRepository.save(q);
		
		return true;
	}
	
	@Override
	public boolean deleteQualification(Long qid) {
		qualificationEntryRepository.deleteById(qid);
		
		return true;
	}
	
	
	//for current user only //add qualification and update point
	@Override
	public boolean addMyQualification(Long qualificationId) {
		Optional<QualificationEntry> q=qualificationEntryRepository.findById(qualificationId);
		
		if(q.isPresent()) {
			Candidate c=candidateRepository.findByUser(securityService.getLoggedInUser());
			List<QualificationEntry> qlist;
			if(c.getQualifications()==null) {
				qlist=new ArrayList<>();
				
				
			}else {
				 qlist=c.getQualifications();
				
			}
			qlist.add(q.get());
			c.setQualifications(qlist);
			candidateRepository.save(c);
			
			candidateservice.updateCandidatePoints(c);
			
			return true;
		}else {return false;}
		
	}

	@Override
	public boolean removeMyQualification(Long qualificationId) {
		Optional<QualificationEntry> q=qualificationEntryRepository.findById(qualificationId);
		if(q.isPresent()) {
			Candidate c=candidateRepository.findByUser(securityService.getLoggedInUser());
			List<QualificationEntry> qlist;
			if(c.getQualifications()==null) {
				qlist=new ArrayList<>();
			}else {
				 qlist=c.getQualifications();
				for(QualificationEntry qe:qlist) {
					if(qe.getId().equals(q.get().getId())) {
						qlist.remove(qe);
					}
				}
			}
			c.setQualifications(qlist);
			candidateRepository.save(c);
			candidateservice.updateCandidatePoints(c);
			return true;
		}else {return false;}
		
	}
	
	
	
	
	
	
	@Override
	public List<QualificationEntry> getCandidateQualifications(User u){
		List<QualificationEntry> qlist=candidateRepository.getUserqualifications(u);
		return qlist;
	}
	
	
	@Override
	public List<QualificationEntry> getAllQualifications(){
		List<QualificationEntry> qlist=qualificationEntryRepository.findAll();
		return qlist;
	}
	
	@Override
	public boolean saveQualificationLinkForSP(QualificationLinkDto dto) {
		
		QualificationLink ql=new QualificationLink();
		Optional<QualificationEntry> q=qualificationEntryRepository.findById(dto.getQualificationId());
		Optional<SelectionProcess> sp=selectionProcessRepository.findById(dto.getSpid());
		
		
		
		if(q.isPresent()&& sp.isPresent()) {
			ql.setQulification(q.get());
			ql.setSelectionProcess(sp.get());
			ql.setPoints(dto.getPoints());
			
			qualificationLinkRepository.save(ql);
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean removeQualificationLinkForSP(Long qlinkId) {
		
		qualificationLinkRepository.deleteById(qlinkId);
		
		
		
		
		return true;
	}
	
	
	@Override
	public List<QualificationLinkDto> getQualificationLinksForSP(long selectionProcessid){
		List<QualificationLink> qll=qualificationLinkRepository.getQualificationLinkListForSProcess(selectionProcessid);
		
		
		
		List<QualificationLinkDto> qlldto=qll.stream().map((QualificationLink ql)->{
			QualificationLinkDto dto=new QualificationLinkDto();
			dto.setId(ql.getId());
			dto.setPoints(ql.getPoints());
			
//			QualificationEntry qe=qualificationLinkRepository.getQualificationSProcess(selectionProcessid);
//			SelectionProcess sp=qualificationLinkRepository.getSProcess(selectionProcessid);
			Object[] test0=qualificationLinkRepository.getTestData(ql.getId());
//			Object test1=qualificationLinkRepository.getTestData(ql.getId());

			Object[] test=(Object[])test0[0];
			
			if(test.length>0) {
				QualificationEntry qe=(QualificationEntry)test[2];
				SelectionProcess sp=(SelectionProcess)test[1];
				
				dto.setQualificationId(qe.getId());
				dto.setQulificationName(qe.getName());
				dto.setsPname(sp.getName());
				dto.setsPuniqueId(sp.getUniqueId());
			}
			
//			dto.setQualificationId(qe.getId());
//			dto.setQulificationName(qe.getName());
//			dto.setsPname(sp.getName());
//			dto.setsPuniqueId(sp.getUniqueId());
			return dto;
		}).collect(Collectors.toList());
		
		
		return qlldto;
	}
	
	@Override
	public boolean addUserQualification(Long qid) {
		boolean success=false;
		
		User curuser=securityService.getLoggedInUser();
		
		List<QualificationEntry> qlist=getCandidateQualifications(curuser);
		
		QualificationEntry q=qualificationEntryRepository.getOne(qid);
		
		qlist.add(q);
		
		Candidate c=candidateRepository.findByUser(curuser);
		c.setQualifications(qlist);
		candidateRepository.save(c);
		
	//	candidateservice.updateCandidatePoints(c);
		
		return success;
	}
	@Override
	public boolean removeUserQualification(Long qid) {
		boolean success=false;
		
		User curuser=securityService.getLoggedInUser();
		
		List<QualificationEntry> qlist=getCandidateQualifications(curuser);
		
		QualificationEntry q=qualificationEntryRepository.getOne(qid);
		
		boolean contains=qlist.contains(q);
		
//		qlist.add(q);
		List<QualificationEntry> qlisto=qlist.stream().filter(qe->{
			if(qe.getId().equals(qid)) {
				return false;
			}else {
				return true;
			}
			
			}).collect(Collectors.toList());
		
		
		
		Candidate c=candidateRepository.findByUser(curuser);
		c.setQualifications(qlisto);
		candidateRepository.save(c);
		
		
		candidateservice.updateCandidatePoints(c);
		
		return success;
	}
	
	
	
	
	
	
	
	
}
