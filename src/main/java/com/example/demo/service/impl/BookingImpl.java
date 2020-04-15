package com.example.demo.service.impl;

import com.example.demo.data.entity.Booking;
import com.example.demo.data.entity.Employee;
import com.example.demo.data.entity.Office;
import com.example.demo.data.repository.BookingRepository;
import com.example.demo.data.repository.EmployeeRepository;
import com.example.demo.data.repository.OfficeRepository;
import com.example.demo.dto.BookingDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.api.BookingService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class BookingImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final EmployeeRepository employeeRepository;
    private final OfficeRepository officeRepository;
    private MapperFacade bookingMapperFacade;

    @Autowired
    @Qualifier("BookingMapper")
    public void setRatingMapperFacade(MapperFacade bookingMapperFacade) {
        this.bookingMapperFacade = bookingMapperFacade;
    }

    @Override
    public void addBooking(BookingDTO bookingDTO) throws NotFoundException {


        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf1.parse(bookingDTO.getDate());

            Office checkOffice = officeRepository.getOfficeAvailability(bookingDTO.getOid(), date, bookingDTO.getStime(), bookingDTO.getEtime());

            if (checkOffice != null) {
                //office already used
                throw new NotFoundException("Office already booked.");
            } else {
                //office not used
                Booking booking = new Booking();
                booking.setDate(date);
                booking.setOffice_Name(bookingDTO.getName());
                booking.setBooker(bookingDTO.getBooker());
                booking.setStime(bookingDTO.getStime());
                booking.setEquipments(bookingDTO.getEquipments());
                booking.setParticipants(bookingDTO.getParticipants());
                booking.setEtime(bookingDTO.getEtime());
                Employee employee;
                Office office;

                //find employee by id
                employee = employeeRepository.findById(bookingDTO.getEid()).orElse(null);
                if (employee != null) {
                    booking.setEmployee(employee);

                    //find office by id
                    office = officeRepository.findById(bookingDTO.getOid()).orElse(null);
                    if (office != null) {
                        booking.setOffice(office);
                        //check office available
                        bookingRepository.save(booking);
                    } else {
                        throw new NotFoundException("Office not found.");
                    }
                } else {
                    throw new NotFoundException("Employee not found.");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BookingDTO> getBookings() {
        List<Booking> bookings = bookingRepository.getBookings();
        List<BookingDTO> bookingDTOS;

        bookingDTOS = bookings.stream()
                .map(this::convertBookingToBookingDTO)
                .collect(Collectors.toList());

        return bookingDTOS;
    }

    @Override
    public List<BookingDTO> getAllPreviousBooking() {
        List<Booking> bookings = bookingRepository.getBookingByDateBefore();
        List<BookingDTO> bookingDTOS;

        bookingDTOS = bookings.stream()
                .map(this::convertBookingToBookingDTO)
                .collect(Collectors.toList());

        return bookingDTOS;
    }

    @Override
    public List<BookingDTO> getBookingById(Integer id) {
        List<Booking> bookings = bookingRepository.getBookingById(id);
        List<BookingDTO> bookingDTOS;

        bookingDTOS = bookings.stream()
                .map(this::convertBookingToBookingDTO)
                .collect(Collectors.toList());

        return bookingDTOS;
    }

    @Override
    public List<BookingDTO> getBookingsByEid(Integer eId) {
        List<Booking> bookings = bookingRepository.getBookingsByEid(eId);
        List<BookingDTO> bookingDTOS;

        bookingDTOS = bookings.stream()
                .map(this::convertBookingToBookingDTO)
                .collect(Collectors.toList());

        return bookingDTOS;
    }

    @Override
    public List<BookingDTO> getBookingsByOid(Integer oId) {
        List<Booking> bookings = bookingRepository.getBookingsByOid(oId);
        List<BookingDTO> bookingDTOS;

        bookingDTOS = bookings.stream()
                .map(this::convertBookingToBookingDTO)
                .collect(Collectors.toList());

        return bookingDTOS;
    }

    @Override
    public void deleteAllBookings() {
        bookingRepository.deleteAll();

    }

    @Override
    public void updateBooking(BookingDTO bookingDTO) throws NotFoundException {
        Booking booking = bookingRepository.findById(bookingDTO.getId()).orElse(null);
        Date date = null;
        try {
            date = (Date) new SimpleDateFormat("yyyy-mm-dd").parse(bookingDTO.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (booking != null) {
            booking.setDate(date);
            Employee employee = employeeRepository.findById(bookingDTO.getEid()).orElse(null);
            booking.setEmployee(employee);
            Office office = officeRepository.findById(bookingDTO.getOid()).orElse(null);
            booking.setOffice(office);
            bookingRepository.save(booking);
        } else {
            // throw exception
            throw new NotFoundException("Booking not found.");

        }

    }

    @Override
    public void deleteBookingById(Integer id) {
        bookingRepository.deleteById(id);
    }


    /**
     * Method to convert booking to BookingDTO
     *
     * @param booking to be converted
     * @return a bookingDTO
     */
    private BookingDTO convertBookingToBookingDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingMapperFacade.map(booking, bookingDTO);
        bookingDTO.setEid(booking.getEmployee().getId());
        bookingDTO.setOid(booking.getOffice().getId());
        bookingDTO.setName(booking.getOffice_Name());
        return bookingDTO;
    }

}
