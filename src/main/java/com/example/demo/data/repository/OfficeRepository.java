package com.example.demo.data.repository;

import com.example.demo.data.entity.Office;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OfficeRepository extends CrudRepository<Office, Integer> {
    // custom queries

    /**
     * Get office by id
     *
     * @param id of office to find
     * @return an office
     */
    Office getOfficeById(Integer id);

    /**
     * Get all offices in database
     *
     * @return a list of offices
     */
    @Query(value = "SELECT * FROM office", nativeQuery = true)
    List<Office> getOffices();


    /**
     * Get office by name
     *
     * @param name of office to find
     * @return an office
     */
    @Query(value = "SELECT * FROM office WHERE name LIKE CONCAT(CONCAT('%', :name), '%')", nativeQuery = true)
    List<Office> getOfficeByName(@Param("name") String name);

    /**
     * Get office availability to prevent conflicts while booking
     *
     * @param id    of office
     * @param date  date of booking
     * @param stime start time of booking
     * @param etime end time of bokking
     * @return an office or not
     */
    @Query(value = "SELECT * FROM booking INNER JOIN office ON booking.oid= :id WHERE date = :date AND stime = :stime AND etime = :etime ", nativeQuery = true)
    Office getOfficeAvailability(@Param("id") Integer id, @Param("date") Date date, @Param("stime") String stime, @Param("etime") String etime);

    /**
     * Get offices with bookings
     *
     * @return a list of bookings
     */
    @Query(value = "SELECT * FROM office LEFT JOIN booking ON office.id = booking.oid", nativeQuery = true)
    List<Office> getOfficesWithRespectiveBookings();

    /**
     * Get all offices and all bookings
     *
     * @return a list of offices
     */
    @Query(value = "SELECT * FROM office LEFT JOIN booking ON office.id = booking.oid " +
            "UNION SELECT * FROM office RIGHT JOIN booking ON office.id = booking.oid", nativeQuery = true)
    List<Office> getOfficesWithAllBookings();

}
