package com.example.rentalservice.service;

import com.example.rentalservice.model.Movie;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class RentalService {

    RestTemplate restTemplate;

    public RentalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private void handleMovieServiceException(Exception e, HttpStatus defaultStatus, String errorMessage) {
        if (e instanceof HttpClientErrorException) {
            HttpClientErrorException httpClientErrorException = (HttpClientErrorException) e;
            throw new HttpClientErrorException(httpClientErrorException.getStatusCode(), errorMessage);
        } else if (e instanceof HttpServerErrorException) {
            HttpServerErrorException httpServerErrorException = (HttpServerErrorException) e;
            throw new HttpServerErrorException(httpServerErrorException.getStatusCode(), errorMessage);
        } else if (e instanceof ResourceAccessException) {
            throw new HttpServerErrorException(HttpStatus.GATEWAY_TIMEOUT, errorMessage);
        } else {
            throw new HttpServerErrorException(defaultStatus, errorMessage);
        }
    }

    public Movie getMovie(int id) {
        String url = "http://localhost:8080/movie/movies/" + id;
        try {
            return restTemplate.getForObject(url, Movie.class);
        } catch (Exception e) {
            handleMovieServiceException(e, HttpStatus.NOT_FOUND, "Failed to get movie with id " + id);
            return null;
        }
    }

    public void returnMovie(int id) {
        String url = "http://localhost:8080/movie/movies/returnMovie/" + id;
        try {
            restTemplate.exchange(url, HttpMethod.PUT, null, Void.class);
        } catch (Exception e) {
            handleMovieServiceException(e, HttpStatus.NOT_FOUND, "Failed to return movie with id " + id);
        }
    }

    public void rentMovie(int id) {
        String url = "http://localhost:8080/movie/movies/rentMovie/" + id;
        try {
            restTemplate.exchange(url, HttpMethod.PUT, null, Void.class);
        } catch (Exception e) {
            handleMovieServiceException(e, HttpStatus.NOT_FOUND, "Failed to rent movie with id " + id);
        }
    }
}
