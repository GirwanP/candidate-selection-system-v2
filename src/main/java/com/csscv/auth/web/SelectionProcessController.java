package com.csscv.auth.web;

import com.csscv.auth.annotation.IsAdmin;
import com.csscv.auth.dto.QualificationEntryDto;
import com.csscv.auth.dto.QualificationLinkDto;
import com.csscv.auth.dto.RecruiterDto;
import com.csscv.auth.dto.SelectionProcessDto;
import com.csscv.auth.dto.SelectionProcessLinkDto;
import com.csscv.auth.dto.SkillLinkDto;
import com.csscv.auth.entities.Candidate;
import com.csscv.auth.entities.QualificationEntry;
import com.csscv.auth.entities.QualificationType;
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

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SelectionProcessController {
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

	
	
	
	
	
	
	/**
	 * 1done rank and sp details
	 * 
	 * @param model
	 * @param spid
	 * @return
	 */
	@GetMapping("/sprank")
	public String getSPlist(Model model, @RequestParam(name = "spid") Long spid) {


		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)||userService.isCurrentUserRecruiter(curuser)) {

			List<SelectionProcessLinkDto> sprank = selectionProcessService.getRankList(spid);

			Optional<SelectionProcessDto> spdetail = selectionProcessService.getSProcessSummary(spid);

			if (spdetail.isPresent()) {
				model.addAttribute("sp", spdetail.get());
			}
			model.addAttribute("username", curuser.getUsername());
			// model.addAttribute("qlist", qlist);
			model.addAttribute("sprank", sprank);

			
			if(userService.isCurrentUserAdmin(curuser)) {
				return "admin/selectionProcessRank";

			}else {
				return "recruiter/selectionProcessRank";
			}
		} else {
			return "redirect:/";
		}


	}
	/**
	 * setting -- list of sp similar to admin dashboard page
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/splist")
	public String getSPlist(Model model) {

		User curuser = securityService.getLoggedInUser();
		Optional<User> curusero= Optional.ofNullable(curuser);

		if (curusero.isPresent()  ) {
			model.addAttribute("username", curuser.getUsername());
			List<SelectionProcessDto> splist;
			
			if(userService.isCurrentUserAdmin(curuser)) {
				 splist= selectionProcessService.getAllSProcessSummaryPageable(0, 75);
				 model.addAttribute("splist", splist);
				 
				 return "admin/selectionProcesslist";
			}else if(userService.isCurrentUserRecruiter(curuser)){
				
				 splist= selectionProcessService.getAllSProcessSummaryPageableRecruiter(0, 75, curuser);
				model.addAttribute("splist", splist);
				
				return "recruiter/selectionProcesslist";
			}else if(userService.isCurrentUserCandidate(curuser)){
				// only view not applied list
				
				splist= selectionProcessService.getCandidateNotAppliedSProcessSummaryPageable(0, 75);
				
				
				
				
				 model.addAttribute("splist", splist);
				return "selectionProcesslistca";
			}
			return "redirect:/";
		} else {
			return "redirect:/";
		}

	}
	
	/**
	 * detail and configuration for sp ql list
	 * 
	 * @param model
	 * @return
	 */
	@Autowired 
	SkillsService skillsService;
	// spdetails?spid=
	@GetMapping("/spdetails")
	public String getSPdetail(Model model, @RequestParam(name = "spid") Long spid

	) {

		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)||userService.isCurrentUserRecruiter(curuser)) {
			
			List<QualificationLinkDto> qll = qualificationsSercice.getQualificationLinksForSP(spid);
			Optional<SelectionProcessDto> spd = selectionProcessService.getSProcessSummary(spid);
			List<QualificationEntry> qlist = qualificationsSercice.getAllQualifications();
			
			
			List<SkillLinkDto> sll = skillsService.getSkillLinkForSP(spid);
			List<Skill> slist = skillsService.getAllSkills();
			
			boolean curuserIsTheCreator=spd.get().getCreatorUserId().equals(curuser.getId())?true:false;
			

			
			model.addAttribute("username", curuser.getUsername());
			model.addAttribute("qll", qll);
			model.addAttribute("sp", spd.get());
			model.addAttribute("qlist", qlist);
			
			
			model.addAttribute("sll", sll);
			model.addAttribute("slist", slist);
			model.addAttribute("curuserIsTheCreator", curuserIsTheCreator);

			if(userService.isCurrentUserAdmin(curuser)) {
				return "admin/selectionProcessDetail";	
			}
			
			return "recruiter/selectionProcessDetail";
		} else {
			return "redirect:/";
		}

	}
	
	
	@PostMapping("/addnewjob")
	public String addjob(Model model, @ModelAttribute SelectionProcessDto job

	) {

		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)||userService.isCurrentUserRecruiter(curuser)) {
			
			
			SelectionProcess sprocess=new SelectionProcess();
			sprocess.setClosingDate(LocalDate.of(3030, 2, 1));
			sprocess.setOpenDate(LocalDate.now());
			sprocess.setName(job.getName());
			sprocess.setUniqueId(sprocess.getName()+(new Random()).nextInt(5000));
			sprocess.setCreator(curuser);
			sprocess=selectionProcessRepository.save(sprocess);

			
			
			return "redirect:/splist";
		} else {
			return "redirect:/";
		}

	}
	
	@GetMapping("/applyjob")
	public String applyForJob(@RequestParam(name = "spid") Long spid ){
		
		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserCandidate(curuser)) {
			selectionProcessService.createNewSelectionProcessLink(curuser, spid);
			
			
			return "redirect:/splist";
		} else {
			return "redirect:/";
		}
		
	}

}
