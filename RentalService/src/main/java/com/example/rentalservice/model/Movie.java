package com.example.rentalservice.model;

public class Movie {

    private int id;
    private String name;
    private MovieCategory movieCategory;

    private String description;
    private int isAvailable;

    public Movie(){

    }

    public Movie(int id, String name, MovieCategory movieCategory, String description, int isAvailable) {
        this.id = id;
        this.name = name;
        this.movieCategory = movieCategory;
        this.description = description;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MovieCategory getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(MovieCategory movieCategory) {
        this.movieCategory = movieCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
    }
}
