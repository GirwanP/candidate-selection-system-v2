package com.csscv.auth.serviceImpl;

import com.csscv.auth.entities.Role;
import com.csscv.auth.entities.User;
import com.csscv.auth.repository.RoleRepository;
import com.csscv.auth.repository.UserRepository;
import com.csscv.auth.service.BugMail;
import com.csscv.auth.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }
    
    @Override
    public void saveCandidateUser(User user) {
    	 user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
         Role r=roleRepository.findByName("candidate");
        Set<Role> rset=new HashSet<>();
        rset.add(r);
        
         user.setRoles(rset);
         userRepository.save(user);
    }
    
    @Override
    public void saveAdminUser(User user) {
    	 user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
         Role r=roleRepository.findByName("admin");
        Set<Role> rset=new HashSet<>();
        rset.add(r);
        
         user.setRoles(rset);
         userRepository.save(user);
    }
    
    

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    
    @Override
    public boolean disableUser(String username) {
        
    	try {
    	User u=userRepository.findByUsername(username);
        u.setActive(false);
        userRepository.save(u);
    	}catch(Exception e) {
    		return false;
    	}
    	return true;
    }
    
    @Override
    public boolean activateUser(String username) {
        
    	try {
    	User u=userRepository.findByUsername(username);
        u.setActive(true);
        userRepository.save(u);
    	}catch(Exception e) {
    		return false;
    	}
    	return true;
    }
    
    @Override
    public boolean isCurrentUserAdmin(User u) {
        
    	try {
    	Role r=roleRepository.findByName("admin");
    	
    		if(u.getRoles().contains(r)) {
    			return true;
    		}
    		
    	}catch(Exception e) {
    		return false;
    	}
    	return false;
    
    }
    @Override
    public boolean isCurrentUserCandidate(User u) {
        
    	try {
    	Role r=roleRepository.findByName("candidate");
    	
    		if(u.getRoles().contains(r)) {
    			return true;
    		}
    		
    	}catch(Exception e) {
    		return false;
    	}
    	return false;
    }
    
    
//    @Autowired
//    UserService userService;
    @Override
    public boolean isCurrentUserRecruiter(User u) {
        
    	try {
    	Role r=roleRepository.findByName("recruiter");
    	
    		if(u.getRoles().contains(r)) {
    			return true;
    		}
    		
    	}catch(Exception e) {
    		return false;
    	}
    	return false;
    }
    
    
    @Override
    public boolean initiatePasswordReset(String username,String reseturl) {
    	User u=findByUsername(username);
    	
//    	u.setEmail("pritap24@gmail.com");
    	u.setEmail(username);
    	
    	u.setPasswordResetInitiated(true);
    	String resetCode=""+(new Random()).nextInt(5000);
    	u.setLatestResetCode(resetCode);
    	
    	String emailbody="Your password reset code is "+resetCode+"<a href='"+reseturl + ">"+" Please click this link to reset your password.</a>";

    	BugMail.mailSender(u.getEmail(), "Account Password Reset requested", emailbody);
    	
    	return false;
    }
    @Override
    public boolean resetPassword(User userForm) {
    	
    	User u=userRepository.findByUsername(userForm.getUsername());
    			
    	 u.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
    	 u.setPasswordResetInitiated(false);
    	return false;
    }
    
    @Override
    public boolean checkPasswordResetCriteria(String username,String resetCode) {
    	
    	User u=userRepository.findByUsername(username);
    			if(u.getLatestResetCode().equals(resetCode) && u.isPasswordResetInitiated()) {
    				return true;
    			}
    	 
    	return false;
    }
    
}
