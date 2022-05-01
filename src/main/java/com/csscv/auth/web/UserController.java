package com.csscv.auth.web;

import com.csscv.auth.annotation.IsCandidate;
import com.csscv.auth.dto.SelectionProcessDto;
import com.csscv.auth.dto.SelectionProcessLinkDto;
import com.csscv.auth.entities.Candidate;
import com.csscv.auth.entities.QualificationEntry;
import com.csscv.auth.entities.QualificationType;
import com.csscv.auth.entities.Role;
import com.csscv.auth.entities.SelectionProcess;
import com.csscv.auth.entities.User;
import com.csscv.auth.repository.QualificationTypeRepository;
import com.csscv.auth.repository.RoleRepository;
import com.csscv.auth.repository.SelectionProcessRepository;
import com.csscv.auth.service.BugMail;
import com.csscv.auth.service.CandidateService;
import com.csscv.auth.service.QualificationsSercice;
import com.csscv.auth.service.SecurityService;
import com.csscv.auth.service.SelectionProcessService;
import com.csscv.auth.service.UserService;
import com.csscv.auth.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
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
    
    @Autowired
    private Environment env;

    

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
    	userForm.setUsername(userForm.getEmail());
    	userValidator.validate(userForm, bindingResult);
        
        
        if (bindingResult.hasErrors()) {
            return "registration";
        }
//        if(!org.springframework.util.StringUtils.isEmpty(session.getAttribute("activeuserEmail"))){
//			return "redirect:getCustomerPortal";
//		}
        
/*
 * 
 *         @RequestMapping(value = "/customerlogin", method = RequestMethod.POST)
    	public String login(HttpSession session, @ModelAttribute Customer c, Model model) {
    		logger.info("submitted data:" + c.getEmail() +" p:"+ c.getPassword());
    		
    		if (cdao.login(c.getEmail(), c.getPassword())) {
    			logger.info("login successful");

    			session.setAttribute("activeuserEmail", c.getEmail());
    			session.setMaxInactiveInterval(MyConstants.maxInactiveSessionDuration); 
    			
*/
//        if(userService.findByUsername(userForm.getUsername())==null) {
        userForm.setActive(true);
        userService.saveCandidateUser(userForm);
//        }
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
    	User curuser=securityService.getLoggedInUser();
    	
//    	SelectionProcess sprocess= selectionProcessRepository.findByName("Default Selection Process");
//    	selectionProcessService.getRankList(sprocess);
    	
//    	Role arole=roleRepository.findByName("admin");
//    	Role arole=roleRepository.findByName("admin");

    	boolean isadmin=false,iscandidate=false,isrecruiter=false;
    	
    	for(Role r:curuser.getRoles()) {
    		if (r.getName().contains("admin")) {isadmin=true;}
    		if (r.getName().contains("candidate")) {iscandidate=true;}
    		if (r.getName().contains("recruiter")) {isrecruiter=true;}
    		
    	}
    	
    	if(iscandidate) {
    		
        	List<SelectionProcessLinkDto> list=selectionProcessService.getSPLForCurUser();

    		model.addAttribute("username",curuser.getUsername() );
        	model.addAttribute("slplist", list);
            return "welcomeCandidate";
    	}
    	
    	if(isadmin) {
    		List<SelectionProcessDto> list=selectionProcessService.getAllSProcessSummaryPageable(0, 75);
    		
    		model.addAttribute("username",curuser.getUsername() );
        	model.addAttribute("slplist", list);
        	 return "admin/adminDashboard";
    	}
    	
    	if(isrecruiter) {
    		
    		List<SelectionProcessDto> list= selectionProcessService.getAllSProcessSummaryPageableRecruiter(0, 75, curuser);
    		
    		model.addAttribute("username",curuser.getUsername() );
        	model.addAttribute("slplist", list);
        	 return "welcomeRecruiter";
    	}
    	
       
        return "welcomeCandidate";
    }
    
    
    @GetMapping("/resetpassword")
    public String resetpassword(Model model,HttpServletRequest request
    		) {
    	model.addAttribute("userForm", new User());
    	
//    	String contextPath=request.getContextPath();
    	String port=env.getProperty("server.port");
    	//  http://localhost:8081/login
    	String url="http://localhost:"+port+"/resetpassword";
    	String username="Dipendra";
    	
    	
    	userService.initiatePasswordReset(username, url);
    	
		
    	
//    	User curuser=securityService.getLoggedInUser();
    		return "passwordreset";
    }
    

    @GetMapping("/forgetpassword")
    public String forgetpassword(Model model) {
    	model.addAttribute("userForm", new User());
    	
    	return "forgetpassword";
    }
    
    
    
  @PostMapping("/forgetpassword")
  public String forgetpasswordp(@ModelAttribute("userForm") User userForm) {
  
	  String reseturl="http://localhost:8081/resetpassword";
	  userService.initiatePasswordReset(userForm.getUsername(), reseturl);
	  
	  return "redirect:/";
  }
  
    
    @PostMapping("/resetpassword")
    public String resetpasswordp(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
      
//    	userValidator.validate(userForm, bindingResult);
        // validation
        Errors errors=bindingResult;
//        Object o, Errors errors) {
//            User user = (User) o;

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
            if (userForm.getUsername().length() < 5 || userForm.getUsername().length() > 32) {
                errors.rejectValue("username", "Size.userForm.username");
            }
           

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
            if (userForm.getPassword().length() < 5 || userForm.getPassword().length() > 32) {
                errors.rejectValue("password", "Size.userForm.password");
            }

            if (!userForm.getPasswordConfirm().equals(userForm.getPassword())) {
                errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
            }
            
            
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "latestResetCode", "NotEmpty");
            if (userForm.getUsername().length() < 2 || userForm.getUsername().length() > 100) {
                errors.rejectValue("latestResetCode", "Size.userForm.latestResetCode");
            }
            
            if(!userService.checkPasswordResetCriteria(userForm.getUsername(), userForm.getLatestResetCode())) {
            	errors.rejectValue("latestResetCode", "Invalid.userForm.latestResetCode");
            }
            

        if (bindingResult.hasErrors()) {
            return "resetpassword";
        }

//        userForm.setActive(true);
        userService.resetPassword(userForm);
        
        
        
        
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    } 
    
    
    
    
    @IsCandidate(testmessage="This is test annotation message")
    @GetMapping("/test")
    public String testc(Model model) {
    	
		SelectionProcess sprocess= selectionProcessRepository.findByName("Default Selection Process");

    	selectionProcessService.getRankList(sprocess);
    	
    	
    	List<SelectionProcessLinkDto> list=selectionProcessService.getSPLForCurUser();
    	
    	User curuser=securityService.getLoggedInUser();
    	model.addAttribute("username",curuser.getUsername() );
    	model.addAttribute("slplist", list);
    	
    	
    	
    	
        return "welcomeCandidate";
//        return "test13";
    	
//    	return "ompaymentdetail";
    }
    
}
