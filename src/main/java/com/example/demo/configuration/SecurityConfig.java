package com.example.demo.configuration;

import com.example.demo.security.CustomAuthenticationProvider;
import com.example.demo.security.JWTFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

/**
 * {@link SecurityConfig} configures Spring Security and defines the authentication method that is used to identify
 * and authenticate users.
 */

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * {@link CustomAuthenticationProvider} instance for the Custom authentication.
     */
    private CustomAuthenticationProvider customAuthenticationProvider;

    /**
     * {@link JWTFilter} instance for the JWT Filter.
     */
    private JWTFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/authorize/**").permitAll()
                .antMatchers("/api/v1/**").authenticated()
                .and()
                .addFilterBefore((Filter) jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Define an authentication provider for authentication purposes.
     *
     * @param authenticationManagerBuilder {@link AuthenticationManagerBuilder} instance
     * @throws Exception Error that occurred during authentication
     */
    @Autowired
    protected void configureGlobal(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
