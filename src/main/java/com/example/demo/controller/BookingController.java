package com.example.demo.controller;


import com.example.demo.dto.BookingDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.api.BookingService;
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
public class BookingController {

    private BookingService bookingService;

    /**
     * Add a new booking to database
     *
     * @param bookingDTO of new booking to be added
     * @throws NotFoundException
     */
    @PostMapping(value = "/booking/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            bookingService.addBooking(bookingDTO);
            return ResponseEntity.ok("");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Get a list of all bookings
     *
     * @return a list of BookingDTO
     */
    @GetMapping(value = "/bookings/get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getBookings() {
        try {
            return ResponseEntity.ok(bookingService.getBookings());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Get booking by booking id
     *
     * @param id of booking to find
     * @return a BookingDTO
     */
    @GetMapping(value = "/booking/getById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getBookingById(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(bookingService.getBookingById(id));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Get bookings by employee id
     *
     * @param eId of employee to which bookings are associated
     * @return a list of BookingDTO
     */
    @GetMapping(value = "/booking/getByEid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getBookingsByEid(@RequestParam Integer eId) {
        try {
            return ResponseEntity.ok(bookingService.getBookingsByEid(eId));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Get bookings by office id
     *
     * @param oId of office to which bookings are associated
     * @return a list of BookingDTO
     */
    @GetMapping(value = "/booking/getByOid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getBookingsByOid(@RequestParam Integer oId) {
        try {
            return ResponseEntity.ok(bookingService.getBookingsByOid(oId));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Delete all bookings in database
     */
    @PostMapping(value = "/bookings/delete", produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity deleteBookings() {
        try {
            bookingService.deleteAllBookings();
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Update a booking by providing an updated BookingDTO
     *
     * @param bookingDTO of booking to update
     * @throws NotFoundException
     */
    @PostMapping(value = "/booking/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            bookingService.updateBooking(bookingDTO);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Delete a booking by id
     *
     * @param id of booking to be deleted
     */
    @PostMapping(value = "/booking/deleteById", produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity deleteBookingById(Integer id) {
        try {
            bookingService.deleteBookingById(id);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Get all bookings before current date
     *
     * @return a list of BookingDTO
     */
    @GetMapping(value = "/bookings/getPrevious", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getPreviousBookings() {
        try {
            return ResponseEntity.ok(bookingService.getAllPreviousBooking());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
