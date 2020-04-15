package com.example.demo.controller;

import com.example.demo.dto.RatingDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.api.RatingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class RatingController {

    private RatingService ratingService;

    /**
     * Method to rate a booking
     *
     * @param ratingDTO of new rating to be added
     */
    @PostMapping(value = "/rating/rateBooking", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity rateBooking(@RequestBody RatingDTO ratingDTO) {

        try {
            ratingService.rateBooking(ratingDTO);
            return (ResponseEntity.ok(""));
        } catch (NotFoundException e) {
            return (ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()));
        } catch (Exception e) {
            return (ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()));
        }
    }

    /**
     * A method to return a list of all ratings by grade in database
     *
     * @return a list of rating
     */
    @GetMapping(value = "/rating/getRatingsWithComments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getRatingsWithComments() {
        try {
            return (ResponseEntity.ok(ratingService.getRatingsWithComments()));
        } catch (Exception e) {
            return (ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()));
        }
    }

    /**
     * A method to return all ratings with the specified grade
     *
     * @return a list of ratings
     */
    @GetMapping(value = "/rating/getRatingsBySortedGrade", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getRatingsBySortedGrades() {
        try {
            return (ResponseEntity.ok(ratingService.getRatingsBySortedGrade()));
        } catch (Exception e) {
            return (ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()));
        }
    }

    /**
     * A method to return all ratings with the specified grade
     *
     * @param grade of rating
     * @return a list of ratings
     */
    @GetMapping(value = "/rating/getRatingsByGrade", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getRatingsByGrades(@RequestParam Integer grade) {
        try {
            return (ResponseEntity.ok(ratingService.getRatingsByGrades(grade)));
        } catch (Exception e) {
            return (ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()));
        }
    }
}
