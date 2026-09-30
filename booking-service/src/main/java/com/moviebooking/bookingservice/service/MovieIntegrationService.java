package com.moviebooking.bookingservice.service;

import com.moviebooking.bookingservice.client.MovieClient;
import com.moviebooking.bookingservice.dto.MovieDto;
import com.moviebooking.bookingservice.exception.MovieNotFoundException;
import com.moviebooking.bookingservice.exception.MovieServiceUnavailableException;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieIntegrationService {

    private final MovieClient movieClient;

    @CircuitBreaker(name = "movieService", fallbackMethod = "getMovieFallback")
    public MovieDto getMovie(Long movieId) {
        try {
            MovieDto movie = movieClient.getMovieById(movieId);
            if (movie == null || movie.getId() == null || movie.getTicketPrice() == null) {
                throw new MovieNotFoundException("Không tìm thấy phim với mã: " + movieId);
            }
            return movie;
        } catch (FeignException.NotFound e) {
            throw new MovieNotFoundException("Không tìm thấy phim với mã: " + movieId);
        }
    }

    public MovieDto getMovieFallback(Long movieId, Throwable throwable) {
        if (throwable instanceof MovieNotFoundException) {
            throw (MovieNotFoundException) throwable;
        }
        if (throwable.getCause() instanceof MovieNotFoundException) {
            throw (MovieNotFoundException) throwable.getCause();
        }
        if (throwable instanceof FeignException fe && fe.status() == 404) {
            throw new MovieNotFoundException("Không tìm thấy phim với mã: " + movieId);
        }
        if (throwable.getCause() instanceof FeignException fe && fe.status() == 404) {
            throw new MovieNotFoundException("Không tìm thấy phim với mã: " + movieId);
        }
        throw new MovieServiceUnavailableException("Hệ thống Movie Service hiện không khả dụng: " + throwable.getMessage());
    }
}
