package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingDTO {

    /**
     * id of booking.
     */
    private Integer id;

    /**
     * date of booking
     */
    private String date;

    /**
     * start time of booking
     */
    private String stime;

    /**
     * end time of booking
     */
    private String etime;

    /**
     * name of office booked
     */
    private String name;

    /**
     * name of booker
     */
    private String booker;

    /**
     * id of employee who created booking
     */
    private Integer eid;

    /**
     * id of office where booking will be held
     */
    private Integer oid;


    /**
     * list of equipments requested for booking
     */
    private String equipments;

    /**
     * list of participants invited for booking
     */
    private String participants;
}
