package com.example.demo.controller;

import com.example.demo.dto.AddOfficeDTO;
import com.example.demo.dto.GetOfficeDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.api.OfficeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class OfficeController {

    private OfficeService officeService;

    /**
     * Add new office to database
     *
     * @param addOfficeDTO of office to be added
     */
    @PostMapping(value = "/office/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addOffice(@RequestBody AddOfficeDTO addOfficeDTO) {
        try {
            officeService.addOffice(addOfficeDTO);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/offices/get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getOffices() {
        try {
            return ResponseEntity.ok(officeService.getOffices());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Get office by id
     *
     * @param id of office to find
     * @return a GetOfficeDTO
     */
    @GetMapping(value = "/office/getById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getOfficeById(Integer id) {
        try {
            return ResponseEntity.ok(officeService.getOfficeById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Get office image by id
     *
     * @param id of image office to find
     * @return an image
     */
    @GetMapping(value = "/office/getImage/{id}")
    public void getOfficeImage(@PathVariable(value = "id") Integer id, HttpServletResponse response) throws IOException {
        GetOfficeDTO getOfficeDTO = officeService.getOfficeById(id);
        byte[] image = getOfficeDTO.getImage();
        if (image != null && image.length > 0) {
            response.setContentType(String.valueOf(MediaType.IMAGE_JPEG));
            response.setContentLength(image.length);
            response.getOutputStream().write(image);
        }
        response.getOutputStream().close();
    }

    /**
     * Get office by name
     *
     * @param name of office to find
     * @return a GetOfficeDTO
     */
    @GetMapping(value = "/office/getByName", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getOfficeByName(String name) {
        try {
            return ResponseEntity.ok(officeService.getOfficeByName(name));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Update an office by providing an updated OfficeDTO
     *
     * @param getOfficeDTO of office to update
     * @throws NotFoundException
     */
    @PostMapping(value = "/office/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateOffice(@RequestBody GetOfficeDTO getOfficeDTO) {
        try {
            officeService.updateOffice(getOfficeDTO);
            return ResponseEntity.ok("");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Delete office by id
     *
     * @param id of office to delete
     */
    @PostMapping(value = "/office/deleteById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteOfficeById(Integer id) {
        try {
            officeService.deleteOffice(id);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Delete all offices
     */
    @PostMapping(value = "/offices/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteOffices() {
        try {
            officeService.deleteOffices();
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Get office with their respective bookings
     *
     * @return a list of GetOfficeDTO
     */
    @GetMapping(value = "/offices/getWithRespectiveBookings", produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity getEmployeesWithRespectiveBookings() {
        try {
            return ResponseEntity.ok(officeService.getOfficesWithRespectiveBookings());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Get all offices and all bookings
     *
     * @return a list of GetOfficeDTO
     */
    @GetMapping(value = "/offices/getAllBookings", produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity getOfficesWithAllBookings() {
        try {
            return ResponseEntity.ok(officeService.getOfficesWithAllBookings());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
