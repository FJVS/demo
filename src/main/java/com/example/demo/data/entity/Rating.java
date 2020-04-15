package com.example.demo.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "rating")
@NoArgsConstructor
public class Rating {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**
     * id of rating
     */
    private Integer id;

    /**
     * comment of rating
     */
    private String comment;

    /**
     * grade of rating
     */
    private Integer grade;

    /**
     * id of employee who rated the booking
     */
    private Integer rater_Id;

    //Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid")
    /**
     * the booking for this rating
     */
    private Booking booking;

}
