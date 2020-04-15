package com.example.demo.dto;

import lombok.Data;

@Data
public class LoginDTO {

    /**
     * UPN of employee.
     */
    private String email;

    /**
     * Password of employee.
     */
    private String password;

    private Boolean rememberMe;
}
