package com.example.demo.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "booking")
@NoArgsConstructor

public class Booking {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
     * name of office
     */
    private String office_Name;

    /**
     * name of booker
     */
    private String booker;

    /**
     * list of equipments required for the booking
     */
    private String equipments;

    /**
     * list of participants invited for booking
     */
    private String participants;

    //Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eid")
    /**
     * employee that made the booking
     */
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oid")
    /**
     * employee that made the booking
     */
    private Office office;

    //Relationships
    @OneToMany(mappedBy = "booking")
    /**
     * list of ratings for this booking
     */
    private List<Rating> ratingList = new ArrayList<>();

}
