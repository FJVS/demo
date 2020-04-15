package com.example.demo.security;

import com.example.demo.data.entity.Employee;
import com.example.demo.data.repository.EmployeeRepository;
import com.example.demo.exception.ApplicationAuthenticationException;
import com.example.demo.service.api.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link CustomAuthenticationProvider} defines the provider requires for authentication.
 */

@Component
@AllArgsConstructor
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private AuthenticationService authentificationService;
    private EmployeeRepository employeeRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication token = null;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        List<String> roleTypes;
        roleTypes = getUserRoles(authentication.getName());
        if (!roleTypes.isEmpty()) {
            roleTypes.forEach(roleType ->
                    grantedAuthorities.add(new SimpleGrantedAuthority(roleType)));
        }

        try {
            if (authentificationService.authenticateUser(authentication.getName(), authentication.getCredentials().toString())) {
                token = new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), grantedAuthorities);
            }
        } catch (ApplicationAuthenticationException e) {
            e.printStackTrace();
        }
        return token;
    }

    private List<String> getUserRoles(String email) {
        final List<String> roles = new ArrayList<String>();
        Employee employee = employeeRepository.findByEmail(email);
        if (employee != null) {
            roles.add(employee.getRole());
        }
        return roles.stream().distinct().sorted().collect(Collectors.toList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}
