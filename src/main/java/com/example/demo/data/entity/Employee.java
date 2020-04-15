package com.example.demo.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "employee")
@NoArgsConstructor

public class Employee {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
     * password of employee
     */
    private String password;

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

    //Relationships
    @OneToMany(mappedBy = "employee")
    /**
     * list of bookings created by this employee
     */
    private List<Booking> bookingList = new ArrayList<>();
}
