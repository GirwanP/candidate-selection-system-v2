package com.csscv.auth;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.xml.ws.soap.Addressing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.csscv.auth.entities.Address;
import com.csscv.auth.entities.Candidate;
import com.csscv.auth.entities.QualificationEntry;
import com.csscv.auth.entities.QualificationLink;
import com.csscv.auth.entities.QualificationType;
import com.csscv.auth.entities.Recruiter;
import com.csscv.auth.entities.Role;
import com.csscv.auth.entities.SelectionProcess;
import com.csscv.auth.entities.SelectionProcessLink;
import com.csscv.auth.entities.Skill;
import com.csscv.auth.entities.SkillLevel;
import com.csscv.auth.entities.User;
import com.csscv.auth.repository.AddressRepository;
import com.csscv.auth.repository.CandidateRepository;
import com.csscv.auth.repository.QualificationEntryRepository;
import com.csscv.auth.repository.QualificationLinkRepository;
import com.csscv.auth.repository.QualificationTypeRepository;
import com.csscv.auth.repository.RecruiterRepository;
import com.csscv.auth.repository.RoleRepository;
import com.csscv.auth.repository.SelectionProcessLinkRepository;
import com.csscv.auth.repository.SelectionProcessRepository;
import com.csscv.auth.repository.SkillLevelRepository;
import com.csscv.auth.repository.SkillRepository;
import com.csscv.auth.repository.UserRepository;
import com.csscv.auth.service.CandidateService;

@Component
public class CustomStartupTaskExecutor {

	
	@Autowired
	SkillLevelRepository skillLevelRepository;
	@Autowired
	SkillRepository skillRepository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	SelectionProcessRepository selectionProcessRepository;
	
	
	@Autowired
	QualificationTypeRepository qualificationTypeRepository;
	
	@Autowired
	QualificationEntryRepository qualificationEntryRepository;
	@Autowired
	QualificationLinkRepository qualificationLinkRepository;
	
	@Autowired
	CandidateRepository candidateRepository;
	
	@Autowired
	CandidateService candidateService;
	
	
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	SelectionProcessLinkRepository selectionProcessLinkRepository;
	
	
	@Autowired 
	RecruiterRepository recruiterRepository;
	
	@PostConstruct
	public void createDefaultAdmin() {
		System.out.println("Inside create admin");
		
		User u=userRepository.findByUsername("admin");
		System.out.println("Looked for admin");
		if(u==null) {
			BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
			
			u=new User();
			u.setUsername("admin");
			u.setPassword(encoder.encode("123456"));
			
			Role r=new Role();
			r.setName("admin");
			r=roleRepository.save(r);
			Set<Role> rs=new HashSet<>();
			rs.add(r);
			u.setRoles(rs);
			u.setActive(true);
			
			userRepository.save(u);
			
			Role r2=new Role();
			r2.setName("candidate");
			r2=roleRepository.save(r2);
			
			Role r3=new Role();
			r3.setName("recruiter");
			r3=roleRepository.save(r3);
			
			User ur=new User();
			ur.setUsername("recruiter");
			ur.setPassword(encoder.encode("123456"));
			ur.setEmail("defaultcomp@gmail.com");
			
			Set<Role> rs3=new HashSet<>();
			rs3.add(r3);
			ur.setRoles(rs3);
			ur.setActive(true);
			
			userRepository.save(ur);
			System.out.println(" created recruiter user");
			
			Recruiter rec=new Recruiter();
			
			Address a1=new Address();
			a1.setCountry("Nepal");
			a1.setMunicipalty("Kathmandu");
			a1.setState("Bagmati");
			addressRepository.save(a1);
			
			rec.setAddress(a1);
			rec.setCompanyName("Default Company");
			rec.setEmail("defaultcomp@gmail.com");
			rec.setPanNo("1254622335");
			rec.setPhoneNo("985054251");
			rec.setUser(ur);
			recruiterRepository.save(rec);
			
			
			QualificationType qtype=new QualificationType();
			qtype.setName("UniversityDegree");
			qtype=qualificationTypeRepository.save(qtype);
			
			QualificationType qtype2=new QualificationType();
			qtype2.setName("Vocational Training");
			qtype2=qualificationTypeRepository.save(qtype2);
			
			QualificationType qtype3=new QualificationType();
			qtype3.setName("Internship");
			qtype3=qualificationTypeRepository.save(qtype3);
			
			QualificationType qtype4=new QualificationType();
			qtype4.setName("Work Experience");
			qtype4=qualificationTypeRepository.save(qtype4);
			
			
			
			
			QualificationEntry e1=new QualificationEntry();
			e1.setName("Bachelor Of engineering");
			e1.setLength(Period.ofYears(4));
			e1.setType(qtype);
			e1=qualificationEntryRepository.save(e1);
			
			QualificationEntry e2=new QualificationEntry();
			e2.setName("Masters Of engineering");
			e2.setLength(Period.ofYears(2));
			e2.setType(qtype);
			e2=qualificationEntryRepository.save(e2);

			
			
			SelectionProcess sprocess=new SelectionProcess();
			sprocess.setClosingDate(LocalDate.of(3030, 2, 1));
			sprocess.setOpenDate(LocalDate.now());
			sprocess.setName("Default Selection Process");
			sprocess.setUniqueId(sprocess.getName()+(new Random()).nextInt(5000));
			sprocess.setCreator(userRepository.findByUsername("admin"));
			sprocess=selectionProcessRepository.save(sprocess);
			
			
			QualificationLink l1=new QualificationLink();
			l1.setPoints(100l);
			l1.setQulification(e1);
			l1.setSelectionProcess(sprocess);
			l1=qualificationLinkRepository.save(l1);
			
			QualificationLink l2=new QualificationLink();
			l2.setPoints(50l);
			l2.setQulification(e2);
			l1.setSelectionProcess(sprocess);

			l2=qualificationLinkRepository.save(l2);
			
			Set<QualificationLink> evaluationdata=new HashSet<>();
			evaluationdata.add(l2);
			evaluationdata.add(l1);
//			sprocess.setEvaluationdata(evaluationdata);
			sprocess=selectionProcessRepository.save(sprocess);

			
			
			User candidate1user=new User();
			candidate1user.setUsername("Dipendra");
			candidate1user.setPassword(encoder.encode("123456"));
			candidate1user.setActive(true);
			candidate1user.setProfileCreated(true);
			
			Set<Role> rset=new HashSet<>();
			rset.add(r2);
			candidate1user.setRoles(rset);
			
			userRepository.save(candidate1user);
			
			Address a=new Address();
			a.setCountry("Nepal");
			a.setMunicipalty("Chitwan");
			a.setState("Bagmati");
			addressRepository.save(a);
			
			Candidate c=new Candidate();
			
			c.setUser(candidate1user);
			c.setFirstName("Dipendra");
			c.setLastName("Hamal");
			c.setAge((byte)30);
			c.setEmail("hamaldipendra@gmail.com");
			c.setMiddleName("");
			c.setPhoneNo("84653128465");
			c.setAddress(a);
			
			
			List<QualificationEntry> qlist=new ArrayList<QualificationEntry>();
			qlist.add(e1);
			qlist.add(e2);
			
			List<SelectionProcessLink> splist=new ArrayList<>();
			
			SelectionProcessLink splink=new SelectionProcessLink();
			splink.setSprocess(sprocess);
			
			splink=selectionProcessLinkRepository.save(splink);
			splist.add(splink);
			
			
			c.setQualifications(qlist);
			c.setSubscribedSelectionProcessess(splist);
			
			candidateRepository.save(c);
			splink.setCandidate(c);
			splink=selectionProcessLinkRepository.save(splink);
			
			candidateService.updateCandidatePoints(c);
			
			SkillLevel lv1=new SkillLevel();
			lv1.setName("Beginer");
			lv1=skillLevelRepository.save(lv1);
			
			SkillLevel lv2=new SkillLevel();
			lv2.setName("Intermediate");
			lv2=skillLevelRepository.save(lv2);
			
			SkillLevel lv3=new SkillLevel();
			lv3.setName("Expert");
			lv3=skillLevelRepository.save(lv3);
			
			Skill sk1=new Skill();
			sk1.setSkillName("Java-Beginer");
			sk1.setSkillLevel(lv1);
			skillRepository.save(sk1);

			Skill sk2=new Skill();
			sk1.setSkillName("Java-Intermediate");
			sk1.setSkillLevel(lv1);
			skillRepository.save(sk2);
			
			Skill sk3=new Skill();
			sk1.setSkillName("Java-Expert");
			sk1.setSkillLevel(lv1);
			skillRepository.save(sk3);
			
			
			
			
		}
	}
}
