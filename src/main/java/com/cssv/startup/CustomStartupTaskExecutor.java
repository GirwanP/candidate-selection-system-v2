package com.cssv.startup;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csscv.auth.entities.Role;
import com.csscv.auth.entities.User;
import com.csscv.auth.repository.RoleRepository;
import com.csscv.auth.repository.UserRepository;

@Component
public class CustomStartupTaskExecutor {
/**
 * This class in this package (i.e. different that package of application ) is not found by 
 * spring and thi will simply not execute
 */
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	
	@PostConstruct
	public void createDefaultAdmin() {
		System.out.println("Inside create admin");
		
		User u=userRepository.findByUsername("admin");
		if(u==null) {
			u=new User();
			u.setUsername("admin");
			u.setPassword("123456");
			
			Role r=new Role();
			r.setName("admin");
			r=roleRepository.save(r);
			Set<Role> rs=new HashSet<>();
			rs.add(r);
			u.setRoles(rs);
			
			userRepository.save(u);
			
			System.out.println(" created default admin user");
		}
	}
}
