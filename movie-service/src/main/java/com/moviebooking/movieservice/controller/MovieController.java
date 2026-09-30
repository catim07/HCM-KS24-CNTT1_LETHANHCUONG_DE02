package com.moviebooking.movieservice.controller;

import com.moviebooking.movieservice.dto.MovieResponse;
import com.moviebooking.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @PostMapping
    public ResponseEntity<MovieResponse> createMovie(@RequestBody MovieResponse movieResponse) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.createMovie(movieResponse));
    }
}
