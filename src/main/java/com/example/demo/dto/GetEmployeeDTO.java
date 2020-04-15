package com.example.demo.dto;

import com.example.demo.data.entity.BookingDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GetEmployeeDTO {

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

    /**
     * list of bookings made by the employee
     */
    private List<BookingDetails> bookingDetails = new ArrayList<>();

}
