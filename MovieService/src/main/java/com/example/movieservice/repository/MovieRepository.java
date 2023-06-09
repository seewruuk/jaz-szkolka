package com.example.movieservice.repository;


import com.example.movieservice.model.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface MovieRepository extends JpaRepository<Movie, Integer> {

//    List<Movie> findAll();
//    Movie save(Movie movie);
//    Movie deleteById(int id);
//
//    Movie findMovieById(int id);

        @Query("SELECT m FROM Movie m WHERE m.id = :id")
        Optional<Movie> findById(@Param("id") int id);

        @Query("update Movie m set m.isAvailable = :status where m.id = :id")
        @Modifying
        @Transactional
        void updateAvailableStatus(int id, int status);

}
