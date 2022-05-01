package com.csscv.auth.web;

import com.csscv.auth.annotation.IsAdmin;
import com.csscv.auth.dto.QualificationEntryDto;
import com.csscv.auth.dto.QualificationLinkDto;
import com.csscv.auth.dto.SelectionProcessDto;
import com.csscv.auth.dto.SelectionProcessLinkDto;
import com.csscv.auth.dto.SkillDto;
import com.csscv.auth.dto.SkillLinkDto;
import com.csscv.auth.entities.Candidate;
import com.csscv.auth.entities.QualificationEntry;
import com.csscv.auth.entities.QualificationType;
import com.csscv.auth.entities.Role;
import com.csscv.auth.entities.SelectionProcess;
import com.csscv.auth.entities.SelectionProcessLink;
import com.csscv.auth.entities.Skill;
import com.csscv.auth.entities.SkillLevel;
import com.csscv.auth.entities.User;
import com.csscv.auth.repository.QualificationTypeRepository;
import com.csscv.auth.repository.RoleRepository;
import com.csscv.auth.repository.SelectionProcessRepository;
import com.csscv.auth.repository.SkillLevelRepository;
import com.csscv.auth.repository.SkillRepository;
import com.csscv.auth.service.CandidateService;
import com.csscv.auth.service.QualificationsSercice;
import com.csscv.auth.service.SecurityService;
import com.csscv.auth.service.SelectionProcessService;
import com.csscv.auth.service.SkillsService;
import com.csscv.auth.service.UserService;
import com.csscv.auth.validator.UserValidator;

import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {
	@Autowired
	private UserService userService;

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

	@GetMapping("/qualifications")
	public String cqualification(Model model) {
		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)||userService.isCurrentUserRecruiter(curuser)) {
			List<QualificationEntry> qlist = qualificationsSercice.getAllQualifications();

			List<QualificationType> qtlist = qualificationTypeRepository.findAll();

			model.addAttribute("username", curuser.getUsername());
			model.addAttribute("qlist", qlist);
			model.addAttribute("qtlist", qtlist);

			return "admin/qualification";
		} else {
			return "redirect:/";
		}

		// curuser.get
		// Candidate c=candidateSercice.getCandidateForuser(curuser);

	}

	@PostMapping("/addqualification")
	public String addqualification(Model model, @ModelAttribute QualificationEntryDto qualification
	// ,@RequestParam int years
	// ,@RequestParam int months
	// ,@RequestParam int days

	) {

		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)||userService.isCurrentUserRecruiter(curuser)) {
			Period p = Period.of(qualification.getYears(), qualification.getMonths(), qualification.getDays());
			qualification.setLength(p);
			boolean qlist = qualificationsSercice.saveQualification(qualification);
			return "redirect:qualifications";
		} else {
			return "redirect:/";
		}

	}

	@GetMapping("/removequalification")
	public String removequalification(Model model, @RequestParam long qid) {

		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)) {
			
			qualificationsSercice.deleteQualification(qid);

			return "redirect:qualifications";
		} else {
			return "redirect:/";
		}

	}

	

	@GetMapping("/deactivatec")
	public String deactivatecandidate(Model model, @RequestParam(name = "candidateid") Long cid) {

		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)) {
			candidateSercice.disableCandidateUser(cid);

			return "redirect:/candidates";
		} else {
			return "redirect:/";
		}

	}

	@GetMapping("/activatec")
	public String activatecandidate(Model model, @RequestParam(name = "candidateid") Long cid) {

		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)) {
			candidateSercice.activateCandidateUser(cid);

			return "redirect:/candidates";
		} else {
			return "redirect:/";
		}

	}

	

	

	

	@PostMapping("/addqlink")
	public String addqualificationLink(Model model, @ModelAttribute QualificationLinkDto qlink) {

		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)) {
			boolean qlist = qualificationsSercice.saveQualificationLinkForSP(qlink);

			return "redirect:qualifications";
		} else {
			return "redirect:/";
		}

	}

	@GetMapping("/removeqlink")
	public String removeqlink(Model model, @RequestParam(name = "qlinkid") Long qlinkid,
			@RequestParam(name = "spid") Long spid, RedirectAttributes attributes) {

		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)) {
			qualificationsSercice.removeQualificationLinkForSP(qlinkid);

			attributes.addAttribute("spid", spid);
			return "redirect:/spdetails?spid=" + spid;
		} else {
			return "redirect:/";
		}

	}
	
	
	
	@Autowired
	SkillRepository skillRepository;
	@Autowired
	SkillLevelRepository skillLevelRepository;
	
	@GetMapping("/skills")
	public String cskills(Model model) {
		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)||userService.isCurrentUserRecruiter(curuser)) {
			
//			List<QualificationEntry> qlist = qualificationsSercice.getAllQualifications();
//			List<QualificationType> qtlist = qualificationTypeRepository.findAll();

			
			List<Skill> slist=skillRepository.findAll();
			List<SkillLevel	> sllist=skillLevelRepository.findAll();
			
			
			model.addAttribute("username", curuser.getUsername());
			model.addAttribute("qlist", slist);
			model.addAttribute("qtlist", sllist);

			return "admin/skills";
		} else {
			return "redirect:/";
		}

		// curuser.get
		// Candidate c=candidateSercice.getCandidateForuser(curuser);

	}
	
	
	@Autowired
	SkillsService skillsService;
	@PostMapping("/addskills")
	public String addskills(Model model, @ModelAttribute SkillDto qualification
	// ,@RequestParam int years
	// ,@RequestParam int months
	// ,@RequestParam int days

	) {

		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)||userService.isCurrentUserRecruiter(curuser)) {
			boolean qlist = skillsService.saveSkills(qualification);
			return "redirect:skills";
		} else {
			return "redirect:/";
		}

	}

	
	@GetMapping("/removeskill")
	public String removeSkill(Model model, @RequestParam long qid) {

		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)) {
			
			skillsService.deleteSkill(qid);

			return "redirect:skills";
		} else {
			return "redirect:/";
		}

	}
	
	
	@GetMapping("/dfdsf")
	public String fsfsdfsd(Model model, @RequestParam long qid) {

		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)) {
			
			skillsService.deleteSkill(qid);

			return "redirect:skills";
		} else {
			return "redirect:/";
		}

	}
	
	
	@PostMapping("/addslink")
	public String addskillLink(Model model, @ModelAttribute SkillLinkDto qlink) {

		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)||userService.isCurrentUserRecruiter(curuser)) {
			boolean qlist = skillsService.saveSkillLinkForSP(qlink);

			return "redirect:qualifications";
		} else {
			return "redirect:/";
		}

	}

	@GetMapping("/removeslink")
	public String removeslink(Model model, @RequestParam(name = "qlinkid") Long qlinkid,
			@RequestParam(name = "spid") Long spid, RedirectAttributes attributes) {

		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)||userService.isCurrentUserRecruiter(curuser)) {
			skillsService.removeSkillLinkForSP(qlinkid);

			attributes.addAttribute("spid", spid);
			return "redirect:/spdetails?spid=" + spid;
		} else {
			return "redirect:/";
		}

	}
	
	
	
	
	@IsAdmin
	@GetMapping("/testad")
	public String testc(Model model) {

		SelectionProcess sprocess = selectionProcessRepository.findByName("Default Selection Process");

		selectionProcessService.getRankList(sprocess);

		List<SelectionProcessLinkDto> list = selectionProcessService.getSPLForCurUser();

		User curuser = securityService.getLoggedInUser();
		model.addAttribute("username", curuser.getUsername());
		model.addAttribute("slplist", list);
		
//		Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
//		Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
//		boolean battery = batteryStatus.BatteryFlag==8; 

		return "welcomeCandidate";
		// return "test13";
		// return "ompaymentdetail";
	}

}
