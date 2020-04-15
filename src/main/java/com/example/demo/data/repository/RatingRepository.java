package com.example.demo.data.repository;

import com.example.demo.data.entity.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends CrudRepository<Rating, Integer> {

    /**
     * Query to select all columns from all ratings in database
     *
     * @return a list of ratings
     */
    @Query(value = "SELECT * FROM rating", nativeQuery = true)
    List<Rating> getRatings();

    /**
     * Query to select all columns from all ratings where grade = grade specified
     *
     * @param grade to search for
     * @return a list of ratings
     */
    @Query(value = "SELECT * FROM rating WHERE grade= :grade", nativeQuery = true)
    List<Rating> getRatingsByGrade(@Param("grade") Integer grade);
}
