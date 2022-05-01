package com.csscv.auth.service;

import com.csscv.auth.entities.User;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);

	User getLoggedInUser();
}
