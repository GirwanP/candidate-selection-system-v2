package com.csscv.auth.service;

import com.csscv.auth.entities.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

	void saveCandidateUser(User user);

	void saveAdminUser(User user);

	boolean disableUser(String username);

	boolean activateUser(String username);

	boolean isCurrentUserAdmin(User u);

//	boolean isCurrentUserCustomer(User u);

	boolean isCurrentUserCandidate(User u);

	boolean isCurrentUserRecruiter(User u);

	boolean resetPassword(User userForm);

	boolean initiatePasswordReset(String username, String contextPath);

	boolean checkPasswordResetCriteria(String username, String resetCode);
}
