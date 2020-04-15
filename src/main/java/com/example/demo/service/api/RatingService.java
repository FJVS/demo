package com.example.demo.service.api;

import com.example.demo.dto.RatingDTO;
import com.example.demo.exception.NotFoundException;

import java.util.List;

public interface RatingService {

    /**
     * Method to rate a booking
     *
     * @param ratingDTO of new rating to be added
     */
    void rateBooking(RatingDTO ratingDTO) throws NotFoundException;

    /**
     * A method to return a list of all ratings with comments in database
     *
     * @return a list of rating
     */
    List<RatingDTO> getRatingsWithComments();

    /**
     * A method to return a list of all ratings by grade in database
     *
     * @return a list of rating
     */
    List<RatingDTO> getRatingsBySortedGrade();

    /**
     * A method to return all ratings with the specified grade
     *
     * @param grade of rating
     * @return a list of ratings
     */
    List<RatingDTO> getRatingsByGrades(Integer grade);
}
