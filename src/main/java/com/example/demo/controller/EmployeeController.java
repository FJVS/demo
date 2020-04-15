package com.example.demo.controller;

import com.example.demo.dto.AddEmployeeDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.api.EmployeeService;
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
@RequestMapping()
public class EmployeeController {

    private EmployeeService employeeService;

    /**
     * Add a new employee to database
     *
     * @param addEmployeeDTO of new employee to be added
     * @throws NotFoundException
     */
    @PostMapping(value = "/employee/add")
    public ResponseEntity addUser(@RequestBody AddEmployeeDTO addEmployeeDTO) {
        try {
            employeeService.addEmployee(addEmployeeDTO);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Delete all bookings in database
     */
    @PostMapping(value = "/employees/delete")
    public ResponseEntity deleteUsers() {
        try {
            employeeService.deleteEmployees();
            return ResponseEntity.ok("Employees deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Delete all bookings in database
     */
    @PostMapping(value = "/employee/deleteById")
    public ResponseEntity deleteUserById(@RequestParam Integer id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Update an employee by providing an updated AddEmployeeDTO
     *
     * @param addEmployeeDTO of employee to update
     * @throws NotFoundException
     */
    @PostMapping(value = "/employee/update")
    public ResponseEntity updateEmployee(@RequestBody AddEmployeeDTO addEmployeeDTO) {
        try {
            employeeService.updateEmployee(addEmployeeDTO);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Get all employees in database
     *
     * @return a list of GetEmployeeDTO
     */
    @GetMapping(value = "/employees/get", produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity getEmployees() {
        try {
            return ResponseEntity.ok(employeeService.getEmployees());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Get all employees sorted by ascending age
     *
     * @return a list of GetEmployeeDTO
     */
    @GetMapping(value = "/employee/getByAscendingAge", produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity getEmployeeByAscendingName() {
        try {
            return ResponseEntity.ok(employeeService.getEmployeesByAscendingAge());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Get an employee by id
     *
     * @param id of employee to find
     * @return a GetEmployeeDTO
     */
    @GetMapping(value = "/employee/getById", produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity getEmployeeById(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(employeeService.getEmployeeById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    /**
     * Get employees with their respective bookings
     *
     * @return a list of GetEmployeeDTO
     */
    @GetMapping(value = "/employees/getWithRespectiveBookings", produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity getEmployeesWithRespectiveBookings() {
        try {
            return ResponseEntity.ok(employeeService.getEmployeesWithRespectiveBookings());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Get all employees and all respective bookings
     *
     * @return a list of GetEmployeeDTO
     */
    @GetMapping(value = "/employees/getAllBookings", produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity getEmployeesWithAllBookings() {
        try {
            return ResponseEntity.ok(employeeService.getEmployeesWithAllBookings());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    /**
     * Get an employee by name
     *
     * @param name of employee to find
     * @return a GetEmployeeDTO
     */
    @GetMapping(value = "/employee/getByName", produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity getEmployeeByName(@RequestParam String name) {
        try {
            //employeeService.getEmployeeByName(name);
            return ResponseEntity.ok(employeeService.getEmployeeByName(name.toLowerCase()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
