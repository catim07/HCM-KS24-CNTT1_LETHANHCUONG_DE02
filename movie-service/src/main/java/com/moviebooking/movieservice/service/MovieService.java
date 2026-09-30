package com.moviebooking.movieservice.service;

import com.moviebooking.movieservice.dto.MovieResponse;

public interface MovieService {
    MovieResponse getMovieById(Long id);
    MovieResponse createMovie(MovieResponse movieResponse);
}
