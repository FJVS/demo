package com.example.demo.service.api;

import com.example.demo.dto.AddEmployeeDTO;
import com.example.demo.dto.GetEmployeeDTO;
import com.example.demo.exception.NotFoundException;

import java.util.List;

public interface EmployeeService {

    /**
     * Add a new employee to database
     *
     * @param addEmployeeDTO of new employee to be added
     * @throws NotFoundException
     */
    void addEmployee(AddEmployeeDTO addEmployeeDTO);

    /**
     * Get all employees in database
     *
     * @return a list of GetEmployeeDTO
     */
    List<GetEmployeeDTO> getEmployees();

    /**
     * Get employees with their respective bookings
     *
     * @return a list of GetEmployeeDTO
     */
    List<GetEmployeeDTO> getEmployeesWithRespectiveBookings();

    /**
     * Get all employees and all respective bookings
     *
     * @return a list of GetEmployeeDTO
     */
    List<GetEmployeeDTO> getEmployeesWithAllBookings();

    /**
     * Get all employees sorted by ascending age
     *
     * @return a list of GetEmployeeDTO
     */
    List<GetEmployeeDTO> getEmployeesByAscendingAge();

    /**
     * Get an employee by name
     *
     * @param name of employee to find
     * @return a GetEmployeeDTO
     */
    List<GetEmployeeDTO> getEmployeeByName(String name);

    /**
     * Get an employee by id
     *
     * @param id of employee to find
     * @return a GetEmployeeDTO
     */
    GetEmployeeDTO getEmployeeById(Integer id);

    /**
     * Update an employee by providing an updated AddEmployeeDTO
     *
     * @param addEmployeeDTO of employee to update
     * @throws NotFoundException
     */
    void updateEmployee(AddEmployeeDTO addEmployeeDTO);

    /**
     * Delete the employee with a specific id in database
     *
     * @param id of employee to be deleted
     */
    void deleteEmployee(Integer id);

    /**
     * Delete all bookings in database
     */
    void deleteEmployees();

}
