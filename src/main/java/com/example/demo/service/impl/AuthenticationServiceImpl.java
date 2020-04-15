package com.example.demo.service.impl;

import com.example.demo.data.entity.Employee;
import com.example.demo.data.repository.EmployeeRepository;
import com.example.demo.exception.ApplicationAuthenticationException;
import com.example.demo.service.api.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final EmployeeRepository employeeRepository;

    @Override
    public boolean authenticateUser(String email, String password) throws ApplicationAuthenticationException {
        Employee employee = employeeRepository.findByEmailAndPassword(email, password);
        if (employee == null) {
            throw new ApplicationAuthenticationException("User not found" + email + " " + password);
        }
        return true;
    }

}
