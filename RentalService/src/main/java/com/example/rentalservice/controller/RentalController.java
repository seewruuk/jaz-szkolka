package com.example.rentalservice.controller;


import com.example.rentalservice.model.Movie;
import com.example.rentalservice.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rental")
public class RentalController {

    RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable int id){
        return ResponseEntity.ok(rentalService.getMovie(id));
    }

    @PutMapping("/returnMovie/{id}")
    public ResponseEntity<Void> returnMovie(@PathVariable int id){
        rentalService.returnMovie(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/rentMovie/{id}")
    public ResponseEntity<Void> rentMovie(@PathVariable int id){
        rentalService.rentMovie(id);
        return ResponseEntity.ok().build();
    }





}
