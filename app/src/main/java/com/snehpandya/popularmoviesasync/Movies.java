package com.snehpandya.popularmoviesasync;

/**
 * Created by sneh.pandya on 22/06/17.
 */

public class Movies {

    private String movieTitle;
    private String movieRating;
    private String movieReleaseDate;
    private String movieDescription;
    private String movieImage;

    public Movies(String title, String rating, String releaseDate, String description, String image) {
        movieTitle = title;
        movieRating = rating;
        movieReleaseDate = releaseDate;
        movieDescription = description;
        movieImage = image;
    }

    public String getMovieTitle() { return movieTitle; }

    public String getMovieRating() { return movieRating; }

    public String getMovieReleaseDate() { return movieReleaseDate; }

    public String getMovieDescription() { return movieDescription; }

    public String getMovieImage() { return movieImage; }
}
