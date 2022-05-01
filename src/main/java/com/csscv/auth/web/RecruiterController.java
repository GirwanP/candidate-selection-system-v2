package com.csscv.auth.web;

import com.csscv.auth.annotation.IsAdmin;
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
import com.csscv.auth.entities.User;
import com.csscv.auth.repository.QualificationTypeRepository;
import com.csscv.auth.repository.RoleRepository;
import com.csscv.auth.repository.SelectionProcessRepository;
import com.csscv.auth.service.CandidateService;
import com.csscv.auth.service.QualificationsSercice;
import com.csscv.auth.service.RecruiterService;
import com.csscv.auth.service.SecurityService;
import com.csscv.auth.service.SelectionProcessService;
import com.csscv.auth.service.UserService;
import com.csscv.auth.validator.RecruiterValidator;
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
public class RecruiterController {
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
	
	@Autowired
	RecruiterValidator recruiterValidator;

	@GetMapping("/registerRecruiter")
	public String recregistration(Model model) {
		model.addAttribute("recruiterForm", new RecruiterDto());
		return "recruiterRegistration";
//			return "redirect:/";
//		}
	}

	
	@PostMapping("/registerRecruiter")
    public String registration(@ModelAttribute("recruiterForm") RecruiterDto userForm, BindingResult bindingResult) {
		
//		userForm.getUsername(null);
		recruiterValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
//       userForm.setActive(true);
        recruiterService.registerRecruiter(userForm);
//        }
//        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }
	
	
	
	@GetMapping("/recruiters")
	public String recruiters(Model model) {

		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)) {
			List<RecruiterDto> clist = recruiterService.getAllRecruiters();
			model.addAttribute("username", curuser.getUsername());
			model.addAttribute("clist", clist);

			return "admin/recruitersList";
		} else {
			return "redirect:/";
		}

	}

	@GetMapping("/recruiterdetail")
	public String recruiterprofile(Model model, @RequestParam(name = "cid") Long cid) {

		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser) || userService.isCurrentUserRecruiter(curuser)) {
			model.addAttribute("username", curuser.getUsername());
			Optional<Recruiter> o = recruiterService.getRecruitereByid(cid);

			if (o.isPresent()) {
				model.addAttribute("candidate", o.get());
				return "admin/recruiterProfile";
			} else {
				return "redirect:/recruiters";
			}
		} else {
			return "redirect:/";
		}

	}
	

	@GetMapping("/deactivaterec")
	public String disableRecruiterUser(Model model, @RequestParam(name = "recruiterid") Long cid) {

		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)) {
			recruiterService.disableRecruiterUser(cid);

			return "redirect:/recruiters";
		} else {
			return "redirect:/";
		}

	}

	@GetMapping("/activaterec")
	public String activatecandidate(Model model, @RequestParam(name = "recruiterid") Long cid) {

		User curuser = securityService.getLoggedInUser();

		if (userService.isCurrentUserAdmin(curuser)) {
			recruiterService.activateRecruiterUser(cid);

			return "redirect:/recruiters";
		} else {
			return "redirect:/";
		}

	}

	

}
