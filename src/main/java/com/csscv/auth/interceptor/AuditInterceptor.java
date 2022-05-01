package com.csscv.auth.interceptor;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.csscv.auth.annotation.IsAdmin;
import com.csscv.auth.annotation.IsCandidate;
import com.csscv.auth.entities.Role;
import com.csscv.auth.entities.User;
import com.csscv.auth.repository.RoleRepository;
import com.csscv.auth.service.SecurityService;
@Component
public class AuditInterceptor implements HandlerInterceptor {

	@Autowired
	SecurityService securityService;
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(handler instanceof HandlerMethod) {
			System.out.println(handler.getClass());
			HandlerMethod hm = (HandlerMethod) handler;
			Method m = hm.getMethod();
			boolean isadmin=m.isAnnotationPresent(IsAdmin.class);
			if (isadmin) {
				System.out.println("IsAdmin annoted url call");
				Optional<User> user=Optional.ofNullable(securityService.getLoggedInUser());
				if(user.isPresent()) {
					
					Role adminrole=roleRepository.findByName("admin");
					if(adminrole!=null) {
					if (user.get().getRoles().contains(adminrole)) {
						
						
					}else {
						response.sendRedirect("/");
						return false;
					}
				}
				
				}else {response.sendRedirect("/login");return false;}
			}
			
		boolean iscustomerMethod=m.isAnnotationPresent(IsCandidate.class);
		
		if(iscustomerMethod) {
			System.out.println("IsCustomer annoted url call");
			Optional<User> user=Optional.ofNullable(securityService.getLoggedInUser());
			if(user.isPresent()) {
				
				Role cusrole=roleRepository.findByName("candidate");
				if(cusrole!=null) {
					Set<Role> roles=user.get().getRoles();
					boolean isUserCandidate=roles.contains(cusrole);
					boolean iscanddate=false;//=Predicate<T>
					
					for(Role r:roles) {
						if(r.getId().equals(cusrole.getId())) {
							iscanddate=true;
							break;
						}
					}
					
				if (iscanddate) {
					
					IsCandidate ics=m.getAnnotation(IsCandidate.class);
					String msg=ics.testmessage();
					int no=ics.testno();
					
					System.out.println("Test received message is'"+msg+"'and test no= "+no);
					
				}else {
					response.sendRedirect("/");
					
					// if false is returned from here further processing of request 
					//  is not done..  but redirects sent before return false will proceed
					return false; 
				}
			}
			
			}else {response.sendRedirect("/login");return false;}
		}
		
		} 
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		HandlerMethod hm = (HandlerMethod) handler;
//		Method m = hm.getMethod();
//		if (m.isAnnotationPresent(IsAdmin.class)) {
//			/*
//			 * System.out.println(m.getAnnotation(IsValid.class).message1());
//			 * System.out.println(m.getAnnotation(IsValid.class).message2());
//			 */
//		}

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
