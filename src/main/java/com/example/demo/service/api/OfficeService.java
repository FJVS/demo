package com.example.demo.service.api;

import com.example.demo.dto.AddOfficeDTO;
import com.example.demo.dto.GetOfficeDTO;
import com.example.demo.exception.NotFoundException;

import java.util.List;

public interface OfficeService {

    /**
     * Add new office to database
     *
     * @param addOfficeDTO of office to be added
     */
    void addOffice(AddOfficeDTO addOfficeDTO);

    /**
     * Method to get all offices in database
     *
     * @return a list of GetOfficeDTO
     */
    List<GetOfficeDTO> getOffices();

    /**
     * Get office with their respective bookings
     *
     * @return a list of GetOfficeDTO
     */
    List<GetOfficeDTO> getOfficesWithRespectiveBookings();

    /**
     * Get all offices and all bookings
     *
     * @return a list of GetOfficeDTO
     */
    List<GetOfficeDTO> getOfficesWithAllBookings();

    /**
     * Get office by id
     *
     * @param id of office to find
     * @return a GetOfficeDTO
     */

    GetOfficeDTO getOfficeById(Integer id);

    /**
     * Get office by name
     *
     * @param name of office to find
     * @return a GetOfficeDTO
     */
    List<GetOfficeDTO> getOfficeByName(String name);

    /**
     * Update an office by providing an updated OfficeDTO
     *
     * @param getOfficeDTO of office to update
     * @throws NotFoundException
     */
    void updateOffice(GetOfficeDTO getOfficeDTO) throws NotFoundException;

    /**
     * Delete office by id
     *
     * @param id of office to delete
     */
    void deleteOffice(Integer id);

    /**
     * Delete all offices
     */
    void deleteOffices();
}
