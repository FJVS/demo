package com.example.demo.service.api;

import com.example.demo.exception.ApplicationAuthenticationException;

public interface AuthenticationService {
    boolean authenticateUser(String email, String password) throws ApplicationAuthenticationException;
}
