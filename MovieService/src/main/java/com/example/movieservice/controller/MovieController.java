package com.example.movieservice.controller;


import com.example.movieservice.MovieServiceApplication;
import com.example.movieservice.model.Movie;
import com.example.movieservice.service.MovieService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies(){
        return ResponseEntity.ok(movieService.findAllMovies());
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Optional<Movie>> getMovie(@PathVariable int id){
        return ResponseEntity.ok(movieService.findMovieById(id));
    }

    @PostMapping("/movies/add")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie){
        return ResponseEntity.ok(movieService.saveMovie(movie));
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/movies")
    public ResponseEntity<Void> updateMovie(@RequestBody Movie movie){
        movieService.updateMovie(movie);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/movies/rentMovie/{id}")
    public ResponseEntity<Void> rentMovie(@PathVariable int id){
        movieService.rentMovie(id, 0);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/movies/returnMovie/{id}")
    public ResponseEntity<Void> returnMovie(@PathVariable int id){
        movieService.returnMovie(id, 1);
        return ResponseEntity.ok().build();
    }
















//    @GetMapping("/movies/{id}")
//    public ResponseEntity<Movie> getMovieWithId(@PathVariable int id){
//        Movie movie = movieService.findMovieById(id);
//        return ResponseEntity.ok(movie);
//    }
//
//    @GetMapping("/movies")
//    public ResponseEntity<List<Movie>> getAllMovies(){
//        List<Movie> movies = movieService.getAllMovies();
//        return ResponseEntity.ok(movies);
//    }
//
//    @PostMapping("/movies")
//    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie){
//        Movie savedMovie = movieService.saveMovie(movie);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
//    }
//
//    @DeleteMapping("/movies/{id}")
//    public ResponseEntity deleteMovie(@PathVariable int id){
//        movieService.deleteById(id);
//        return ResponseEntity.ok().build();
//    }

//    @DeleteMapping("/movies/{id}")
//    public ResponseEntity<Movie> deleteMovie(@PathVariable int id){
//        Movie movie = movieService.deleteMovieById(id);
//        return ResponseEntity.ok().build();
//    }

//    @PutMapping("/movies/{id}")
//    public ResponseEntity<Movie> updateMovie(@PathVariable int id, @RequestBody Movie movie){
//        Movie updatedMovie = movieService.updateMovie(id, movie);
//        return ResponseEntity.ok(updatedMovie);
//    }

//    @PutMapping("/movies/toTrue/{id}")
//    public ResponseEntity<Movie> updateMovieAvailability(@PathVariable int id){
//        Movie updatedMovie = movieService.updateMovieAvailability(id);
//        return ResponseEntity.ok(updatedMovie);
//    }
//    @PutMapping("/movies/toFalse/{id}")
//    public ResponseEntity<Movie> updateMovieAvailableToFalse(@PathVariable int id){
//        return ResponseEntity.ok(movieService.updateMovieAvailableToFalse((id)));
//    }

}
