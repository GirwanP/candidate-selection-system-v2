package com.csscv.auth.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.csscv.auth.entities.Address;
import com.csscv.auth.entities.Candidate;
import com.csscv.auth.entities.QualificationEntry;
import com.csscv.auth.entities.SelectionProcess;
import com.csscv.auth.entities.SelectionProcessLink;
import com.csscv.auth.entities.User;
import com.csscv.auth.repository.AddressRepository;
import com.csscv.auth.repository.CandidateRepository;
import com.csscv.auth.repository.QualificationEntryRepository;
import com.csscv.auth.repository.SelectionProcessLinkRepository;
import com.csscv.auth.repository.SelectionProcessRepository;
import com.csscv.auth.repository.UserRepository;
import com.csscv.auth.service.CandidateService;
import com.csscv.auth.service.PointsService;
import com.csscv.auth.service.SecurityService;
import com.csscv.auth.service.SelectionProcessService;

@Service
public class CandidateServiceImpl implements CandidateService {
	
	
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
	UserRepository userRepository;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	SelectionProcessService selectionProcessService; 
	
	@Override
	public boolean saveCandidateInfo(Candidate c) {
		boolean success=false;
		
		Address a=c.getAddress();
		addressRepository.save(a);
		c.setAddress(a);
		
		User u=securityService.getLoggedInUser();
		c.setActive(u.isActive());
		c.setUser(u);
		
		SelectionProcessLink splink=new SelectionProcessLink();
		SelectionProcess sprocess= selectionProcessRepository.findByName("Default Selection Process");
		splink.setSprocess(sprocess);
		
		splink=selectionProcessLinkRepository.save(splink);
		List<SelectionProcessLink> spllisst=new ArrayList<>();
		spllisst.add(splink);
		
		c.setSubscribedSelectionProcessess(spllisst);
		
		// qualifications are added seperately
		
		
		c=candidateRepository.save(c);
		
		splink.setCandidate(c);
		splink=selectionProcessLinkRepository.save(splink);
		
		
		updateCandidatePoints(c);
		
		success=true;
		
		return success;
		
	}
	
	//update name and related informations
	@Override
	public void updateCandidateInfo(Candidate c) {
		
		Candidate cs=candidateRepository.findByUser(securityService.getLoggedInUser());
		cs.setAddress(c.getAddress());
		cs.setAge(c.getAge());
		cs.setEmail(c.getEmail());
		cs.setFirstName(c.getFirstName());
		cs.setMiddleName(c.getMiddleName());
		cs.setLastName(c.getLastName());
		cs.setPhoneNo(c.getPhoneNo());
		
		
		// qualifications are added seperately
		
		
		candidateRepository.save(cs);
	}
	
	//for current user only //add qualification and update point
	@Override
	public boolean addQualification(Long qualificationId) {
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
			
			updateCandidatePoints(c);
			
			return true;
		}else {return false;}
		
	}

	@Override
	public boolean removeQualification(Long qualificationId) {
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
			updateCandidatePoints(c);
			return true;
		}else {return false;}
		
	}
	
	@Override
	public void addCandidatePointsDefaultSP(Candidate c) {
		
		SelectionProcess sprocess= selectionProcessRepository.findByName("Default Selection Process");

		
		SelectionProcessLink splink=selectionProcessLinkRepository.getSPLForCandidateAndSelectionProcess(c, sprocess);
		
		Long points=pointsService.calculatePoints(c.getQualifications(),c.getSkills(), sprocess);
		
		splink.setScore(points);
		splink=selectionProcessLinkRepository.save(splink);
		
		selectionProcessService.updateRankForSelctionProcess(sprocess.getId());

		
	}
	public void addCandidatePoints(Candidate c) {
		
	}
	
	@Override
	public void updateCandidatePoints(Candidate c) {
		addCandidatePointsDefaultSP(c);
		
	}
	
	public void subscribeToNewSelectionProcess() {
		
		
		
		
	}

	@Override
	public Candidate getCandidateForuser(User u) {
		Candidate c=candidateRepository.findByUser(u);
		return c;
	}
	@Override
	public Optional<Candidate> getCandidateByid(long id) {
		Optional<Candidate> c=candidateRepository.findById(id);
		
		return c;
	}
	
	@Override
	public List<Candidate> getall() {
		List<Candidate> c=candidateRepository.findAll();
		return c;
	}
	
	
	
	public List<QualificationEntry> getCandidateQualifications(User u){
		List<QualificationEntry> qlist=candidateRepository.getUserqualifications(u);
		return qlist;
	}
	@Override
	public boolean disableCandidateUser(long candidateid) {
			User u=candidateRepository.getUser(candidateid);
			u.setActive(false);
			
			Candidate c=candidateRepository.getOne(candidateid);
			c.setActive(false);
			candidateRepository.save(c);
	        userRepository.save(u);

			return true;
	}
	@Override
	public boolean activateCandidateUser(long candidateid) {
		User u=candidateRepository.getUser(candidateid);
		u.setActive(true);
        userRepository.save(u);
        
        Candidate c=candidateRepository.getOne(candidateid);
		c.setActive(true);
		candidateRepository.save(c);

		return true;
	}
	
}
