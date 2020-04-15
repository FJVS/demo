package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.security.SecurityUtils;
import com.example.demo.security.TokenProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthorizationController {

    private AuthenticationManager authenticationManager;

    private TokenProvider tokenProvider;

    @PostMapping(value = "/authorize", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity authenticate(@RequestBody LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication, loginDTO.getRememberMe());
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("id_token", jwt);
            jsonObject.accumulate("id_user", authentication.getPrincipal().toString());
            jsonObject.accumulate("authorities", authentication.getAuthorities());
            jsonObject.accumulate("authenticated", authentication.isAuthenticated());
            return ResponseEntity.ok(jsonObject.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    /**
     * Verify if user is authenticated.
     *
     * @return {@link ResponseEntity} instance to indicate whether the user is authenticated
     * @throws JSONException
     */
    @GetMapping(value = "/authenticated", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity isAuthenticated() throws JSONException {
        String user = SecurityUtils.getCurrentUserLogin();
        if (user != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("username", user);
            return ResponseEntity.ok(jsonObject.toString());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
        }
    }

}
