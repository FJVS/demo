package com.example.demo.service.impl;

import com.example.demo.data.entity.Booking;
import com.example.demo.data.entity.BookingDetails;
import com.example.demo.data.entity.Employee;
import com.example.demo.data.repository.EmployeeRepository;
import com.example.demo.dto.AddEmployeeDTO;
import com.example.demo.dto.GetEmployeeDTO;
import com.example.demo.service.api.EmployeeService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class EmployeeImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private MapperFacade employeeMapperFacade;

    @Autowired
    @Qualifier("EmployeeMapper")
    public void setEmployeeMapperFacade(MapperFacade employeeMapperFacade) {
        this.employeeMapperFacade = employeeMapperFacade;
    }

    @Override
    public void addEmployee(AddEmployeeDTO addEmployeeDTO) {
        Employee employee = new Employee();
        employee.setName(addEmployeeDTO.getName());
        employee.setPosition(addEmployeeDTO.getPosition());
        employee.setAge(addEmployeeDTO.getAge());
        employee.setRole(addEmployeeDTO.getRole());
        repository.save(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteEmployees() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void updateEmployee(AddEmployeeDTO addEmployeeDTO) {
        Employee employee = repository.findById(addEmployeeDTO.getId()).orElse(null);
        if (employee != null) {
            employee.setPosition(addEmployeeDTO.getPosition());
            employee.setAge(addEmployeeDTO.getAge());
            employee.setName(addEmployeeDTO.getName());
            employee.setRole(addEmployeeDTO.getRole());
            repository.save(employee);
        } else {
            //throw exception
        }

    }

    @Override
    public List<GetEmployeeDTO> getEmployees() {
        List<Employee> employees = (List<Employee>) repository.findAll();
        List<GetEmployeeDTO> getEmployeeDTOS;

        getEmployeeDTOS = employees.stream()
                .map(this::convertEmployeeToEmployeeDTO)
                .collect(Collectors.toList());

        return getEmployeeDTOS;
    }

    @Override
    public List<GetEmployeeDTO> getEmployeesByAscendingAge() {
        List<Employee> employees = repository.getEmployeesByAscendingAge();
        List<GetEmployeeDTO> getEmployeeDTOS;

        getEmployeeDTOS = employees.stream()
                .map(this::convertEmployeeToEmployeeDTO)
                .collect(Collectors.toList());

        return getEmployeeDTOS;
    }

    @Override
    public GetEmployeeDTO getEmployeeById(Integer id) {
        Employee employee = repository.findById(id).orElse(null);
        List<BookingDetails> bookingDetails = new ArrayList<>();
        GetEmployeeDTO getEmployeeDTO = new GetEmployeeDTO();
        employeeMapperFacade.map(employee, getEmployeeDTO);
        // Create booking list of employee from booking entity
        for (Booking bookings : employee.getBookingList()) {
            BookingDetails bookingDetail = new BookingDetails();
            bookingDetail.setDate(bookings.getDate());
            bookingDetail.setEtime(bookings.getEtime());
            bookingDetail.setStime(bookings.getStime());
            bookingDetail.setId(bookings.getId());
            bookingDetails.add(bookingDetail);
        }
        getEmployeeDTO.setBookingDetails(bookingDetails);

        return getEmployeeDTO;
    }

    @Override
    public List<GetEmployeeDTO> getEmployeesWithRespectiveBookings() {
        List<Employee> employees = repository.getEmployeesWithRespectiveBookings();
        List<GetEmployeeDTO> getEmployeeDTOS;

        getEmployeeDTOS = employees.stream()
                .map(this::convertEmployeeToEmployeeDTO)
                .collect(Collectors.toList());

        return getEmployeeDTOS;
    }

    @Override
    public List<GetEmployeeDTO> getEmployeesWithAllBookings() {

        List<Employee> employees = repository.getEmployeesWithAllBookings();
        List<GetEmployeeDTO> getEmployeeDTOS;

        getEmployeeDTOS = employees.stream()
                .map(this::convertEmployeeToEmployeeDTO)
                .collect(Collectors.toList());

        return getEmployeeDTOS;

    }

    @Override
    public List<GetEmployeeDTO> getEmployeeByName(String name) {
        List<Employee> employees = repository.getEmployeeByName(name);
        List<GetEmployeeDTO> getEmployeeDTOS;

        getEmployeeDTOS = employees.stream()
                .map(this::convertEmployeeToEmployeeDTO)
                .collect(Collectors.toList());

        return getEmployeeDTOS;
    }

    /**
     * Method to convert employee to EmployeeDTO
     *
     * @param employee to be converted
     * @return a employeeDTO
     */
    private GetEmployeeDTO convertEmployeeToEmployeeDTO(Employee employee) {

        List<BookingDetails> bookingDetails = new ArrayList<>();
        GetEmployeeDTO getEmployeeDTO = new GetEmployeeDTO();
        employeeMapperFacade.map(employee, getEmployeeDTO);
        // Create booking list of employee from booking entity
        for (Booking bookings : employee.getBookingList()) {
            BookingDetails bookingDetail = new BookingDetails();
            bookingDetail.setDate(bookings.getDate());
            bookingDetail.setEtime(bookings.getEtime());
            bookingDetail.setStime(bookings.getStime());
            bookingDetail.setId(bookings.getId());
            bookingDetails.add(bookingDetail);
        }
        getEmployeeDTO.setBookingDetails(bookingDetails);

        return getEmployeeDTO;
    }
}
