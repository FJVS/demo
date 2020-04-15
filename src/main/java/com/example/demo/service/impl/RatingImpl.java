package com.example.demo.service.impl;

import com.example.demo.data.entity.Booking;
import com.example.demo.data.entity.Rating;
import com.example.demo.data.repository.BookingRepository;
import com.example.demo.data.repository.RatingRepository;
import com.example.demo.dto.RatingDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.api.RatingService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class RatingImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final BookingRepository bookingRepository;
    private MapperFacade ratingMapperFacade;

    @Autowired
    @Qualifier("RatingMapper")
    public void setRatingMapperFacade(MapperFacade ratingMapperFacade) {
        this.ratingMapperFacade = ratingMapperFacade;
    }

    @Override
    public void rateBooking(RatingDTO ratingDTO) throws NotFoundException {

        Booking booking = bookingRepository.findById(ratingDTO.getBid()).orElse(null);
        if (booking != null) {
            Rating rating = new Rating();
            rating.setRater_Id(ratingDTO.getRaterId());
            rating.setGrade(ratingDTO.getGrade());
            rating.setComment(ratingDTO.getComment());
            rating.setBooking(booking);
            ratingRepository.save(rating);
        } else {
            throw new NotFoundException("Booking does not exist.");
        }
    }

    @Override
    public List<RatingDTO> getRatingsWithComments() {
        List<Rating> ratings = ratingRepository.getRatings();
        List<RatingDTO> ratingDTOS;

        List<Rating> ratingsWithComments = ratings.stream().filter(rating -> rating.getComment() != null).collect(Collectors.toList());

        ratingDTOS = ratingsWithComments.stream()
                .map(this::convertRatingToRatingDTO)
                .collect(Collectors.toList());

        return ratingDTOS;
    }

    @Override
    public List<RatingDTO> getRatingsBySortedGrade() {
        List<Rating> ratings = ratingRepository.getRatings();
        List<RatingDTO> ratingDTOS;

        List<Rating> ratingsWithComments = ratings.stream().sorted(Comparator.comparingInt(Rating::getGrade).reversed()).collect(Collectors.toList());

        ratingDTOS = ratingsWithComments.stream()
                .map(this::convertRatingToRatingDTO)
                .collect(Collectors.toList());

        return ratingDTOS;
    }

    @Override
    public List<RatingDTO> getRatingsByGrades(Integer grade) {
        List<Rating> ratings = ratingRepository.getRatingsByGrade(grade);
        List<RatingDTO> ratingDTOS;

        ratingDTOS = ratings.stream()
                .map(this::convertRatingToRatingDTO)
                .collect(Collectors.toList());

        return ratingDTOS;
    }

    /**
     * A method to convert a rating to a RatingDTO
     *
     * @return a RatingDTO
     */
    private RatingDTO convertRatingToRatingDTO(Rating rating) {

        RatingDTO ratingDTO = new RatingDTO();
        ratingMapperFacade.map(rating, ratingDTO);
        ratingDTO.setBid(rating.getBooking().getId());
        return ratingDTO;
    }
}
