package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RatingDTO {

    private Integer id;
    private String comment;
    private Integer grade;
    private Integer bid;
    private Integer raterId;
}
