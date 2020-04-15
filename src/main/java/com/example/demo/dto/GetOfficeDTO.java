package com.example.demo.dto;

import com.example.demo.data.entity.BookingDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GetOfficeDTO {

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

    /**
     * photo of office
     */
    @Lob
    private byte[] image;

    /**
     * list of bookings made for the office
     */
    private List<BookingDetails> bookingDetails = new ArrayList<>();
}
