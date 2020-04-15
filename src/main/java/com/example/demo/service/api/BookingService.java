package com.example.demo.service.api;

import com.example.demo.dto.BookingDTO;
import com.example.demo.exception.NotFoundException;

import java.util.List;

public interface BookingService {

    /**
     * Add a new booking to database
     *
     * @param bookingDTO of new booking to be added
     * @throws NotFoundException
     */
    void addBooking(BookingDTO bookingDTO) throws NotFoundException;

    /**
     * Get a list of all bookings
     *
     * @return a list of BookingDTO
     */
    List<BookingDTO> getBookings();

    /**
     * Get all bookings before current date
     *
     * @return a list of BookingDTO
     */
    List<BookingDTO> getAllPreviousBooking();

    /**
     * Get booking by booking id
     *
     * @param id of booking to find
     * @return a BookingDTO
     */
    List<BookingDTO> getBookingById(Integer id);

    /**
     * Get bookings by employee id
     *
     * @param eId of employee to which bookings are associated
     * @return a list of BookingDTO
     */
    List<BookingDTO> getBookingsByEid(Integer eId);

    /**
     * Get bookings by office id
     *
     * @param oId of office to which bookings are associated
     * @return a list of BookingDTO
     */
    List<BookingDTO> getBookingsByOid(Integer oId);

    /**
     * Update a booking by providing an updated BookingDTO
     *
     * @param bookingDTO of booking to update
     * @throws NotFoundException
     */
    void updateBooking(BookingDTO bookingDTO) throws NotFoundException;

    /**
     * Delete all bookings in database
     */
    void deleteAllBookings();

    /**
     * Delete a booking by id
     *
     * @param id of booking to be deleted
     */
    void deleteBookingById(Integer id);

}
