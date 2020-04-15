package com.example.demo.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "office")

public class Office {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**
     * id of office
     */
    private Integer id;

    /**
     * name of office
     */
    private String name;

    /**
     * type of office
     */
    private String type;

    @Lob
    /**
     * photo of office
     */
    private byte[] photo;

    //Relationships
    @OneToMany(mappedBy = "office")
    /**
     * list of bookings for this office
     */
    private List<Booking> bookingList = new ArrayList<>();
}
