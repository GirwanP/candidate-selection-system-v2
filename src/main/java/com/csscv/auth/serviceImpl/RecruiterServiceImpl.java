package com.csscv.auth.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.csscv.auth.dto.QualificationLinkDto;
import com.csscv.auth.dto.RecruiterDto;
import com.csscv.auth.dto.SelectionProcessDto;
import com.csscv.auth.dto.SkillLinkDto;
import com.csscv.auth.entities.Address;
import com.csscv.auth.entities.Candidate;
import com.csscv.auth.entities.QualificationEntry;
import com.csscv.auth.entities.QualificationLink;
import com.csscv.auth.entities.Recruiter;
import com.csscv.auth.entities.Role;
import com.csscv.auth.entities.SelectionProcess;
import com.csscv.auth.entities.SelectionProcessLink;
import com.csscv.auth.entities.Skill;
import com.csscv.auth.entities.SkillLink;
import com.csscv.auth.entities.User;
import com.csscv.auth.repository.AddressRepository;
import com.csscv.auth.repository.CandidateRepository;
import com.csscv.auth.repository.QualificationEntryRepository;
import com.csscv.auth.repository.RecruiterRepository;
import com.csscv.auth.repository.RoleRepository;
import com.csscv.auth.repository.SelectionProcessLinkRepository;
import com.csscv.auth.repository.SelectionProcessRepository;
import com.csscv.auth.repository.SkillLinkRepository;
import com.csscv.auth.repository.SkillRepository;
import com.csscv.auth.repository.UserRepository;
import com.csscv.auth.service.CandidateService;
import com.csscv.auth.service.PointsService;
import com.csscv.auth.service.RecruiterService;
import com.csscv.auth.service.SecurityService;
import com.csscv.auth.service.SelectionProcessService;

@Service
public class RecruiterServiceImpl implements RecruiterService {
	
	@Autowired
	SkillRepository skillRepository;
	@Autowired
	SkillLinkRepository skillLinkRepository;
	@Autowired
	QualificationsServiceImpl qualificationsServiceImpl;
	@Autowired
	RecruiterRepository recruiterRepository;
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
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public boolean registerRecruiter(RecruiterDto recruiter) {
		// TODO Auto-generated method stub
		
		Address a=new Address();
		a.setCountry(recruiter.getCountry());
		a.setMunicipalty(recruiter.getMunicipalty());
		a.setState(recruiter.getState());
		
		User u=new User();
		Recruiter r=new Recruiter();
				
		u.setUsername(recruiter.getEmail());
		u.setPassword(recruiter.getPassword());
		
		Set<Role> rs=new HashSet<>();
		Role rol=roleRepository.findByName("recruiter");
		rs.add(rol);
		u.setRoles(rs);
		u.setActive(true);
		u=userRepository.save(u);
		
		r.setCompanyName(recruiter.getCompanyName());
		r.setEmail(recruiter.getEmail());
		r.setPanNo(recruiter.getPanNo());
		r.setPhoneNo(recruiter.getPhoneNo());
		r.setUser(u);
		
		
		a=addressRepository.save(a);
		r.setAddress(a);
		
		
		recruiterRepository.save(r);
		return true;
	}

	@Override
	public boolean addJob(SelectionProcessDto job) {
		// TODO Auto-generated method stub
		SelectionProcess sprocess=new SelectionProcess();
		sprocess.setClosingDate(LocalDate.of(3030, 2, 1));
		sprocess.setOpenDate(LocalDate.now());
		sprocess.setName(job.getName());
		sprocess.setUniqueId(sprocess.getName()+(new Random()).nextInt(5000));
		sprocess.setCreator(securityService.getLoggedInUser());
		sprocess=selectionProcessRepository.save(sprocess);
		
		
		return false;
	}
	
	
	@Override
	public boolean addSkillLink(SkillLinkDto dto) {
		

		
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
	public boolean addQualificationLink(QualificationLinkDto dto) {
		qualificationsServiceImpl.saveQualificationLinkForSP(dto);
		
		return true;
	}
	
	
	@Override
	public List<RecruiterDto> getAllRecruiters() {
		List<RecruiterDto> rldto=new ArrayList<RecruiterDto>();
		
		List<Recruiter> rlist=recruiterRepository.findAll();
		
		for(Recruiter r:rlist) {
			RecruiterDto rd=new RecruiterDto();
			rd.setCompanyName(r.getCompanyName());
			rd.setEmail(r.getEmail());
			rd.setPanNo(r.getPanNo());
			rd.setId(r.getId());
			rd.setPhoneNo(r.getPhoneNo());
			rd.setUsername(r.getUser().getUsername());
			rd.setMunicipalty(r.getAddress().getMunicipalty());
			rd.setState(r.getAddress().getState());
			rd.setCountry(r.getAddress().getCountry());
			rd.setActive(r.isActive());
			
			rldto.add(rd);
		}
		
		return rldto;
	}
	
	
	@Override
	public boolean disableRecruiterUser(long recruiterid) {
			User u=recruiterRepository.getUser(recruiterid);
			u.setActive(false);
			
			Recruiter c=recruiterRepository.getOne(recruiterid);
			c.setActive(false);
			recruiterRepository.save(c);
	        userRepository.save(u);

			return true;
	}
	
	@Override
	public boolean activateRecruiterUser(long recruiterid) {
		User u=recruiterRepository.getUser(recruiterid);
		u.setActive(true);
        userRepository.save(u);
        
        Recruiter c=recruiterRepository.getOne(recruiterid);
		c.setActive(true);
		recruiterRepository.save(c);

		return true;
	}
	
	
	@Override
	public Optional<Recruiter> getRecruitereByid(long id) {
		Optional<Recruiter> c=recruiterRepository.findById(id);
		
		return c;
	}
}
