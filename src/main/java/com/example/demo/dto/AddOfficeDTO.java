package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@Data
@NoArgsConstructor
public class AddOfficeDTO {

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
}
