package com.csscv.auth.web;

import com.csscv.auth.annotation.IsAdmin;
import com.csscv.auth.annotation.IsCandidate;
import com.csscv.auth.dto.QualificationEntryDto;
import com.csscv.auth.dto.QualificationLinkDto;
import com.csscv.auth.dto.RecruiterDto;
import com.csscv.auth.dto.SelectionProcessDto;
import com.csscv.auth.dto.SelectionProcessLinkDto;
import com.csscv.auth.entities.Candidate;
import com.csscv.auth.entities.QualificationEntry;
import com.csscv.auth.entities.QualificationType;
import com.csscv.auth.entities.Recruiter;
import com.csscv.auth.entities.Role;
import com.csscv.auth.entities.SelectionProcess;
import com.csscv.auth.entities.SelectionProcessLink;
import com.csscv.auth.entities.Skill;
import com.csscv.auth.entities.User;
import com.csscv.auth.repository.QualificationTypeRepository;
import com.csscv.auth.repository.RoleRepository;
import com.csscv.auth.repository.SelectionProcessRepository;
import com.csscv.auth.service.CandidateService;
import com.csscv.auth.service.QualificationsSercice;
import com.csscv.auth.service.RecruiterService;
import com.csscv.auth.service.SecurityService;
import com.csscv.auth.service.SelectionProcessService;
import com.csscv.auth.service.SkillsService;
import com.csscv.auth.service.UserService;
import com.csscv.auth.validator.UserValidator;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CandidateController {
	@Autowired
	private UserService userService;
	@Autowired
	private RecruiterService recruiterService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	SelectionProcessService selectionProcessService;

	@Autowired
	SelectionProcessRepository selectionProcessRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	CandidateService candidateSercice;

	@Autowired
	QualificationTypeRepository qualificationTypeRepository;

	@Autowired
	QualificationsSercice qualificationsSercice;

	
	
	
	@GetMapping("/candidates")
	public String candidates(Model model) {

		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)) {
			List<Candidate> clist = candidateSercice.getall();
			model.addAttribute("username", curuser.getUsername());
			model.addAttribute("clist", clist);

			return "admin/candidateList";
		} else {
			return "redirect:/";
		}

	}

	@GetMapping("/candidatedetail")
	public String candidateprofile(Model model, @RequestParam(name = "cid") Long cid) {

		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser) || userService.isCurrentUserRecruiter(curuser)) {
			model.addAttribute("username", curuser.getUsername());
			Optional<Candidate> o = candidateSercice.getCandidateByid(cid);

			if (o.isPresent()) {
				model.addAttribute("candidate", o.get());
				return "admin/candidateProfile";
			} else {
				return "redirect:/candidates";
			}
		} else {
			return "redirect:/";
		}

	}
	
	
	 @IsCandidate(testmessage="only customer allowed")
	    
	    @PostMapping("/addcandidateinfo")
	    public String addcandidateinfo(Model model,
	    		@ModelAttribute(name="c") Candidate c
	    		,BindingResult br
	    		) {
	    	
	    	boolean success=candidateSercice.saveCandidateInfo(c);
	    	
	    	User curuser=securityService.getLoggedInUser();
	    	
	    	
	    		return "redirect:/cprofile";
	    }
	    
	    @IsCandidate(testmessage="only customer allowed")
	    @GetMapping("/editcandidateinfo")
	    public String editcandidateinfo(Model model,
	    		@ModelAttribute(name="c") Candidate c
	    		,BindingResult br
	    		) {
	    	
	    	
	    	User curuser=securityService.getLoggedInUser();
	    	
	    	
	    		return "redirect:/cprofile";
	    }
	    
	    @IsCandidate(testmessage="only customer allowed")
	    @GetMapping("/myqualifications")
	    public String cqualification(Model model) {
	    	
	    	
	    	User curuser=securityService.getLoggedInUser();
//	    	Candidate c=candidateSercice.getCandidateForuser(curuser);
	    	
	    	List<QualificationEntry> qlist=candidateSercice.getCandidateQualifications(curuser);
	    	List<QualificationEntry> allqlist=qualificationsSercice.getAllQualifications();
	    	
	    	List<QualificationType> qtlist=qualificationTypeRepository.findAll();
	    	
	    	model.addAttribute("username",curuser.getUsername() );
	    	model.addAttribute("qlist", qlist);
	    	model.addAttribute("qtlist", qtlist);
	    	model.addAttribute("allqlist", allqlist);
	    	
	        return "candidateQualifications";
	    }
	    
	    @Autowired
	    SkillsService skillsService;
	    
	    @GetMapping("/myskills")
	    public String cskills(Model model) {
	    	
	    	
	    	User curuser=securityService.getLoggedInUser();
//	    	Candidate c=candidateSercice.getCandidateForuser(curuser);
	    	
	    	List<Skill> qlist=skillsService.getCandidateSkills(curuser);
	    	List<Skill> allqlist=skillsService.getAllSkills();
	    	
	    	List<QualificationType> qtlist=new ArrayList<QualificationType>();//qualificationTypeRepository.findAll();
	    	
	    	model.addAttribute("username",curuser.getUsername() );
	    	model.addAttribute("qlist", qlist);
	    	model.addAttribute("qtlist", qtlist);
	    	model.addAttribute("allqlist", allqlist);
	    	
	        return "candidateSkills";
	    }
	    
	    @IsCandidate(testmessage="only customer allowed")
	    
	    @PostMapping("/addtomql")
	    public String addtoqualification(Model model,
	    		@RequestParam(name="qid") Long qid
	    		) {
	    	User curuser=securityService.getLoggedInUser();
	    	
	    	qualificationsSercice.addUserQualification(qid);
	    	

	    	return "redirect:/myqualifications";
	    }
	    
	    /**remove qualification from my qualification list
	     * 
	     * @param model
	     * @param qid
	     * @return
	     */
	    @IsCandidate(testmessage="only customer allowed")
	    
	    @GetMapping("/rmfrmql")
	    public String removefromqualification(Model model,
	    		@RequestParam(name="qid") Long qid
	    		) {
	    	
	    	
	    	User curuser=securityService.getLoggedInUser();
	    	
	    	qualificationsSercice.removeUserQualification(qid);
	    	
	    		return "redirect:/myqualifications";
	    }
	    
	    @PostMapping("/addtomyskills")
	    public String addtoMyskills(Model model,
	    		@RequestParam(name="qid") Long qid
	    		) {
	    	User curuser=securityService.getLoggedInUser();
	    	
	    	skillsService.addUserSkills(qid);
	    	

	    	return "redirect:/myskills";
	    }
	    
	    /**remove skill from my skills list
	     * 
	     * @param model
	     * @param qid
	     * @return
	     */
	    @IsCandidate(testmessage="only customer allowed")
	    
	    @GetMapping("/rmfromskilllist")
	    public String removefromskl(Model model,
	    		@RequestParam(name="qid") Long qid
	    		) {
	    	
	    	
	    	User curuser=securityService.getLoggedInUser();
	    	
	    	skillsService.removeUserSkill(qid); 
	    	
	    		return "redirect:/myskills";
	    }
	    
	    
	    
	    @IsCandidate(testmessage="only customer allowed")
	    @GetMapping("/cprofile")
	    public String cprofile(Model model) {
	    	
//			SelectionProcess sprocess= selectionProcessRepository.findByName("Default Selection Process");
//	    	selectionProcessService.getRankList(sprocess);
//	    	List<SelectionProcessLinkDto> list=selectionProcessService.getSPLForCurUser();
//	    	model.addAttribute("username", securityService.getLoggedInUser().getUsername());
//	    	model.addAttribute("slplist", list);
	    	
	    	User curuser=securityService.getLoggedInUser();
	    	Candidate c=candidateSercice.getCandidateForuser(curuser);
	    	
	    	model.addAttribute("username",curuser.getUsername() );
	    	model.addAttribute("candidate", c);
	    	
	        return "candidateProfile";
//	        return "test13";
	    	
//	    	return "ompaymentdetail";
	    }
	    
	   
	    
	   
}
