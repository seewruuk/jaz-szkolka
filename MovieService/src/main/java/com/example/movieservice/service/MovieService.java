package com.example.movieservice.service;

import com.example.movieservice.model.Movie;
import com.example.movieservice.model.MovieCategory;
import com.example.movieservice.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findMovieById(int id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with id " + id + " not found");
        }
        return movie;
    }

    public Movie saveMovie(Movie movie) {
        Optional<Movie> isMovieExist = movieRepository.findById(movie.getId());
        if (isMovieExist.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie with id " + movie.getId() + " already exists");
        }
        return movieRepository.save(movie);
    }

    public void deleteMovie(int id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            movieRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with id " + id + " not found");
        }
    }

    public void updateMovie(Movie movie) {
        Optional<Movie> isMovieExist = movieRepository.findById(movie.getId());
        if (isMovieExist.isPresent()) {
            movieRepository.save(movie);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with id " + movie.getId() + " not found");
        }
    }

    public void rentMovie(int id, int status) {
        Optional<Movie> findMovie = movieRepository.findById(id);
        if (findMovie.isPresent()) {
            movieRepository.updateAvailableStatus(id, status);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with id" + id + " not found");
        }
    }

    public void returnMovie(int id, int status) {
        Optional<Movie> findMovie = movieRepository.findById(id);
        if (findMovie.isPresent()) {
            movieRepository.updateAvailableStatus(id, status);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with id" + id + " not found");
        }
    }


//    public Movie updateMovie(int id, Movie movie){
//        Movie movieToUpdate = movieRepository.findMovieById(id);
//        movieToUpdate.setName(movie.getName());
//        movieToUpdate.setMovieCategory(movie.getMovieCategory());
//        movieToUpdate.setDescription(movie.getDescription());
//        movieToUpdate.setIsAvailable(movie.getIsAvailable());
//        return movieRepository.save(movieToUpdate);
//    }


//    public Movie updateMovieAvailability(int id){
//        Movie movieToUpdate = movieRepository.findMovieById(id);
//        movieToUpdate.setIsAvailable(1);
//        return movieRepository.save(movieToUpdate);
//    }

//    public Movie updateMovieAvailableToFalse(int id){
//        Movie movieToUpdate = movieRepository.findMovieById(id);
//        movieToUpdate.setIsAvailable(0);
//        return movieRepository.save(movieToUpdate);
//    }

}
