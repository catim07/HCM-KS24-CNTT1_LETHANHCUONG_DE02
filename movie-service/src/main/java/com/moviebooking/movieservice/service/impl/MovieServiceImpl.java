package com.moviebooking.movieservice.service.impl;

import com.moviebooking.movieservice.dto.MovieResponse;
import com.moviebooking.movieservice.entity.Movie;
import com.moviebooking.movieservice.exception.MovieNotFoundException;
import com.moviebooking.movieservice.repository.MovieRepository;
import com.moviebooking.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    @Cacheable(value = "movies", key = "#id", sync = true)
    public MovieResponse getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Không tìm thấy phim với mã: " + id));
        return mapToResponse(movie);
    }

    @Override
    public MovieResponse createMovie(MovieResponse response) {
        Movie movie = Movie.builder()
                .title(response.getTitle())
                .ticketPrice(response.getTicketPrice())
                .description(response.getDescription())
                .durationInMinutes(response.getDurationInMinutes())
                .build();
        Movie saved = movieRepository.save(movie);
        return mapToResponse(saved);
    }

    private MovieResponse mapToResponse(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .ticketPrice(movie.getTicketPrice())
                .description(movie.getDescription())
                .durationInMinutes(movie.getDurationInMinutes())
                .build();
    }
}
