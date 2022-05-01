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
import com.csscv.auth.dto.SkillDto;
import com.csscv.auth.dto.SkillLinkDto;
import com.csscv.auth.entities.Candidate;
import com.csscv.auth.entities.QualificationEntry;
import com.csscv.auth.entities.QualificationLink;
import com.csscv.auth.entities.QualificationType;
import com.csscv.auth.entities.SelectionProcess;
import com.csscv.auth.entities.SelectionProcessLink;
import com.csscv.auth.entities.Skill;
import com.csscv.auth.entities.SkillLevel;
import com.csscv.auth.entities.SkillLink;
import com.csscv.auth.entities.User;
import com.csscv.auth.repository.CandidateRepository;
import com.csscv.auth.repository.QualificationEntryRepository;
import com.csscv.auth.repository.QualificationLinkRepository;
import com.csscv.auth.repository.QualificationTypeRepository;
import com.csscv.auth.repository.SelectionProcessLinkRepository;
import com.csscv.auth.repository.SelectionProcessRepository;
import com.csscv.auth.repository.SkillLevelRepository;
import com.csscv.auth.repository.SkillLinkRepository;
import com.csscv.auth.repository.SkillRepository;
import com.csscv.auth.service.CandidateService;
import com.csscv.auth.service.PointsService;
import com.csscv.auth.service.QualificationsSercice;
import com.csscv.auth.service.SecurityService;
import com.csscv.auth.service.SkillsService;

@Service
public class SkillsServiceImpl implements SkillsService {
	
	
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
	
	@Autowired
	SkillLevelRepository skilllevelRepository;
	@Autowired
	SkillRepository skillRepository;
	
	@Override
	public boolean saveSkills(SkillDto qualification) {
		
		
		QualificationEntry q=new QualificationEntry();
		Skill s=new Skill();
		SkillLevel l=skilllevelRepository.getOne(qualification.getSkillLevelId());
		
		s.setSkillLevel(l);
		s.setSkillName(qualification.getSkillName());
		s.setValid(true);
		
		
		
		skillRepository.save(s);
		
		return true;
	}
	
	@Override
	public boolean deleteSkill(Long sid) {
		skillRepository.deleteById(sid);
		
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
	public List<Skill> getCandidateSkills(User u){
		List<Skill> qlist=candidateRepository.getUserSkills(u);
		return qlist;
	}
	
	
	@Override
	public List<Skill> getAllSkills(){
		List<Skill> qlist=skillRepository.findAll();
		return qlist;
	}
	
	@Override
	public boolean saveSkillLinkForSP(SkillLinkDto dto) {
		
		SkillLink ql=new SkillLink();
		Optional<Skill> q=skillRepository.findById(dto.getSkillId());
		Optional<SelectionProcess> sp=selectionProcessRepository.findById(dto.getSpid());
		
		
		
		if(q.isPresent()&& sp.isPresent()) {
			ql.setSkill(q.get());
			ql.setSelectionProcess(sp.get());
			ql.setPoints(dto.getPoints());
			
			skillLinkRepository.save(ql);
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean removeSkillLinkForSP(Long qlinkId) {
		
		skillLinkRepository.deleteById(qlinkId);
		
		
		
		
		return true;
	}
	
	@Autowired
	SkillLinkRepository skillLinkRepository;
	@Override
	public List<SkillLinkDto> getSkillLinkForSP(long selectionProcessid){
		List<SkillLink> qll=skillLinkRepository.getSkillLinkListForSProcess(selectionProcessid);
		
		
		
		List<SkillLinkDto> qlldto=qll.stream().map((SkillLink ql)->{
			SkillLinkDto dto=new SkillLinkDto();
			dto.setId(ql.getId());
			dto.setPoints(ql.getPoints());
			
			Object[] test0=skillLinkRepository.getTestData(ql.getId());

			Object[] test=(Object[])test0[0];
			
			if(test.length>0) {
				Skill qe=(Skill)test[2];
				SelectionProcess sp=(SelectionProcess)test[1];
				
				dto.setSkillId(qe.getId());
				dto.setSkillName(qe.getSkillName());
				dto.setsPname(sp.getName());
				dto.setsPuniqueId(sp.getUniqueId());
			}

			
			return dto;
		}).collect(Collectors.toList());
		
		
		return qlldto;
	}
	
	@Override
	public boolean addUserSkills(Long qid) {
		boolean success=false;
		
		User curuser=securityService.getLoggedInUser();
		
		List<Skill> qlist=getCandidateSkills(curuser);
		
		Skill q=skillRepository.getOne(qid);
		
		qlist.add(q);
		
		Candidate c=candidateRepository.findByUser(curuser);
		c.setSkills(qlist);
		candidateRepository.save(c);
		
		candidateservice.updateCandidatePoints(c);
		
		return success;
	}
	@Override
	public boolean removeUserSkill(Long qid) {
		boolean success=false;
		
		User curuser=securityService.getLoggedInUser();
		
		List<Skill> qlist=getCandidateSkills(curuser);
		
		Skill q=skillRepository.getOne(qid);
		
		boolean contains=qlist.contains(q);
		
//		qlist.add(q);
		List<Skill> qlisto=qlist.stream().filter(qe->{
			if(qe.getId().equals(qid)) {
				return false;
			}else {
				return true;
			}
			
			}).collect(Collectors.toList());
		
		
		
		Candidate c=candidateRepository.findByUser(curuser);
		c.setSkills(qlisto);
		candidateRepository.save(c);
		
		
		candidateservice.updateCandidatePoints(c);
		
		return success;
	}


	@Override
	public boolean saveQualification(QualificationEntryDto qualification) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	
	
	
}
