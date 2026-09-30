package com.moviebooking.bookingservice.client;

import com.moviebooking.bookingservice.dto.MovieDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "movie-service", path = "/api/v1/movies")
public interface MovieClient {

    @GetMapping("/{id}")
    MovieDto getMovieById(@PathVariable("id") Long id);
}
