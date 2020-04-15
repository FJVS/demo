package com.example.demo.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class BookingDetails {

    /**
     * id of booking
     */
    private Integer id;

    /**
     * date of booking
     */
    private Date date;

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
    private String office_Name;

    /**
     * name of booker
     */
    private String booker;

    /**
     * list of equipments for the booking
     */
    private String equipments;

    /**
     * list of participants invited for booking
     */
    private String participants;
}
