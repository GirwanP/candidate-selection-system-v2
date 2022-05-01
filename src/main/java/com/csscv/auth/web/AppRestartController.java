package com.csscv.auth.web;

import com.csscv.auth.WebApplication;
import com.csscv.auth.annotation.IsAdmin;
import com.csscv.auth.dto.QualificationEntryDto;
import com.csscv.auth.dto.QualificationLinkDto;
import com.csscv.auth.dto.SelectionProcessDto;
import com.csscv.auth.dto.SelectionProcessLinkDto;
import com.csscv.auth.entities.Candidate;
import com.csscv.auth.entities.QualificationEntry;
import com.csscv.auth.entities.QualificationType;
import com.csscv.auth.entities.Role;
import com.csscv.auth.entities.SelectionProcess;
import com.csscv.auth.entities.SelectionProcessLink;
import com.csscv.auth.entities.User;
import com.csscv.auth.repository.QualificationTypeRepository;
import com.csscv.auth.repository.RoleRepository;
import com.csscv.auth.repository.SelectionProcessRepository;
import com.csscv.auth.service.CandidateService;
import com.csscv.auth.service.QualificationsSercice;
import com.csscv.auth.service.RestartService;
import com.csscv.auth.service.SecurityService;
import com.csscv.auth.service.SelectionProcessService;
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
public class AppRestartController {
     
	/**
	 * require no dependency
	 */
    @GetMapping("/restartcus")
    public void restart() {
    	System.out.println("App will restart");
        WebApplication.restart();
    }
    
    
    /**
     * restart with actuator ,dependency =actuator
     */
    @Autowired
    RestartService restartService;
    @GetMapping("/restartact")
    public void restartact() {
    	System.out.println("App will restart");
    	restartService.restartApp();
    }
    
    /**@gp
     * this worked
     * requires devtools dependency
     */
    @GetMapping("/restartdev")
    public void restartdev() {
    	System.out.println("App will restart");
        org.springframework.boot.devtools.restart.Restarter.getInstance().restart();

    }
    
    public void rffff() {
    	System.out.println("App will restart");
    	System.out.println("test");
        org.springframework.boot.devtools.restart.Restarter.getInstance().restart();
        
    }
    
}