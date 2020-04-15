package com.example.demo.data.repository;

import com.example.demo.data.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    //custom queries

    /**
     * Get employee by name
     *
     * @param name of employee to find
     * @return an employee
     */
    @Query(value = "SELECT * FROM employee WHERE name LIKE CONCAT(CONCAT('%', :name), '%')", nativeQuery = true)
    List<Employee> getEmployeeByName(@Param("name") String name);

    /**
     * Get employees by ascending age
     *
     * @return a list of employees
     */
    @Query(value = "SELECT * FROM employee ORDER BY age", nativeQuery = true)
    List<Employee> getEmployeesByAscendingAge();

    /**
     * Method to find im employee login in is valid
     *
     * @param email    of employee
     * @param password of employee
     * @return employee associated with this login
     */
    Employee findByEmailAndPassword(String email, String password);

    /**
     * Get employees with their respective bookings
     *
     * @return a list of employees
     */
    @Query(value = "SELECT * FROM employee LEFT JOIN booking ON employee.id = booking.eid", nativeQuery = true)
    List<Employee> getEmployeesWithRespectiveBookings();


    /**
     * Get all employees and all bookings
     *
     * @return a list of employees
     */
    @Query(value = "SELECT * FROM employee LEFT JOIN booking ON employee.id = booking.eid " +
            "UNION SELECT * FROM employee RIGHT JOIN booking ON employee.id = booking.eid", nativeQuery = true)
    List<Employee> getEmployeesWithAllBookings();

    Employee findByEmail(String email);
}
