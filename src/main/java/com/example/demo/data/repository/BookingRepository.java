package com.example.demo.data.repository;

import com.example.demo.data.entity.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

    //custom queries

    /**
     * Get all bookings in database
     *
     * @return a list of booking
     */
    @Query(value = "SELECT * FROM booking WHERE date >= current_date", nativeQuery = true)
    List<Booking> getBookings();

    /**
     * Get booking by id
     *
     * @param id of booking to find
     * @return a booking
     */
    @Query(value = "SELECT * FROM booking WHERE id = :id AND date >= current_date", nativeQuery = true)
    List<Booking> getBookingById(Integer id);

    /**
     * Get bookings by employee id
     *
     * @param eId of employee to find respective bookings
     * @return a list of bookings
     */
    @Query(value = "SELECT * FROM booking WHERE eid = :eId AND date >= current_date ", nativeQuery = true)
    List<Booking> getBookingsByEid(@Param("eId") Integer eId);

    /**
     * Get bookings by office id
     *
     * @param oId of office to find respective bookings
     * @return a list of bookings
     */
    @Query(value = "SELECT * FROM booking WHERE oid = :oId AND date >= current_date ", nativeQuery = true)
    List<Booking> getBookingsByOid(@Param("oId") Integer oId);

    /**
     * Get bookings before current date
     *
     * @return a list of bookings
     */
    @Query(value = "SELECT * FROM booking WHERE date < current_date", nativeQuery = true)
    List<Booking> getBookingByDateBefore();


}
