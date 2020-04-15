package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddEmployeeDTO {

    /**
     * id of employee
     */
    private Integer id;

    /**
     * name of employee
     */
    private String name;

    /**
     * email of employee
     */
    private String email;

    /**
     * role of employee
     */

    private String role;

    /**
     * position of employee
     */
    private String position;

    /**
     * age of employee
     */
    private Integer age;

}
