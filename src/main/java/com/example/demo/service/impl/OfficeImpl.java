package com.example.demo.service.impl;

import com.example.demo.data.entity.Booking;
import com.example.demo.data.entity.BookingDetails;
import com.example.demo.data.entity.Office;
import com.example.demo.data.repository.OfficeRepository;
import com.example.demo.dto.AddOfficeDTO;
import com.example.demo.dto.GetOfficeDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.api.OfficeService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class OfficeImpl implements OfficeService {

    private final OfficeRepository officeRepository;
    private MapperFacade officeMapperFacade;

    @Autowired
    @Qualifier("OfficeMapper")
    public void setMapperFacade(MapperFacade officeMapperFacade) {
        this.officeMapperFacade = officeMapperFacade;
    }

    @Override
    public void addOffice(AddOfficeDTO addOfficeDTO) {
        System.out.print(addOfficeDTO);
        Office office = new Office();
        office.setName(addOfficeDTO.getName());
        office.setType(addOfficeDTO.getType());
        office.setPhoto(addOfficeDTO.getImage());
        officeRepository.save(office);
    }

    @Override
    public List<GetOfficeDTO> getOffices() {
        List<Office> offices = officeRepository.getOffices();
        List<GetOfficeDTO> getOfficeDTOS;

        getOfficeDTOS = offices.stream().map(this::convertOfficeToOfficeDTO).collect(Collectors.toList());
        return getOfficeDTOS;
    }

    @Override
    public GetOfficeDTO getOfficeById(Integer id) {
        List<BookingDetails> bookingDetails = new ArrayList<>();
        Office office = officeRepository.getOfficeById(id);
        GetOfficeDTO getOfficeDTO = new GetOfficeDTO();
        getOfficeDTO.setImage(office.getPhoto());
        officeMapperFacade.map(office, getOfficeDTO);
        for (Booking bookings : office.getBookingList()) {
            BookingDetails bookingDetail = new BookingDetails();
            bookingDetail.setDate(bookings.getDate());
            bookingDetail.setEtime(bookings.getEtime());
            bookingDetail.setStime(bookings.getStime());
            bookingDetail.setId(bookings.getId());
            bookingDetails.add(bookingDetail);
        }

        getOfficeDTO.setBookingDetails(bookingDetails);
        return getOfficeDTO;
    }


    @Override
    public List<GetOfficeDTO> getOfficeByName(String name) {
        List<Office> offices = officeRepository.getOfficeByName(name);
        List<GetOfficeDTO> getOfficeDTOS;

        getOfficeDTOS = offices.stream()
                .map(this::convertOfficeToOfficeDTO)
                .collect(Collectors.toList());

        return getOfficeDTOS;
    }

    @Override
    public void updateOffice(GetOfficeDTO getOfficeDTO) throws NotFoundException {
        Office office = officeRepository.findById(getOfficeDTO.getId()).orElse(null);
        if (office != null) {
            office.setName(getOfficeDTO.getName());
            office.setType(getOfficeDTO.getType());
            officeRepository.save(office);
        } else {
            throw new NotFoundException("Office not found.");
        }

    }

    @Override
    public void deleteOffice(Integer id) {
        officeRepository.deleteById(id);
    }

    @Override
    public void deleteOffices() {
        officeRepository.deleteAll();
    }

    @Override
    public List<GetOfficeDTO> getOfficesWithRespectiveBookings() {
        List<Office> offices = officeRepository.getOfficesWithRespectiveBookings();
        List<GetOfficeDTO> getOfficeDTOS;

        getOfficeDTOS = offices.stream()
                .map(this::convertOfficeToOfficeDTO)
                .collect(Collectors.toList());

        return getOfficeDTOS;
    }

    @Override
    public List<GetOfficeDTO> getOfficesWithAllBookings() {
        List<Office> offices = officeRepository.getOfficesWithAllBookings();
        List<GetOfficeDTO> getOfficeDTOS;

        getOfficeDTOS = offices.stream()
                .map(this::convertOfficeToOfficeDTO)
                .collect(Collectors.toList());

        return getOfficeDTOS;
    }

    /**
     * Method to convert office to officeDTO
     *
     * @param office to be converted
     * @return a officeDTO
     */
    private GetOfficeDTO convertOfficeToOfficeDTO(Office office) {
        List<BookingDetails> bookingDetails = new ArrayList<>();
        GetOfficeDTO getOfficeDTO = new GetOfficeDTO();
        officeMapperFacade.map(office, getOfficeDTO);
        getOfficeDTO.setImage(office.getPhoto());
        for (Booking bookings : office.getBookingList()) {
            BookingDetails bookingDetail = new BookingDetails();
            bookingDetail.setDate(bookings.getDate());
            bookingDetail.setEtime(bookings.getEtime());
            bookingDetail.setStime(bookings.getStime());
            bookingDetail.setId(bookings.getId());
            bookingDetails.add(bookingDetail);
        }

        getOfficeDTO.setBookingDetails(bookingDetails);
        return getOfficeDTO;
    }
}
