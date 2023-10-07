package com.movies.rest.controller;

import com.movies.rest.dto.MovieDto;
import com.movies.rest.dto.PagedMovies;
import com.movies.rest.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieDto movieDto) {
        return new ResponseEntity<>(movieService.createMovie(movieDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PagedMovies> getMovie(
            @RequestParam(value = "p", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "s", defaultValue = "5", required = false) int pageSize
    ) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return ResponseEntity.ok(movieService.getAllMovies(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieDto> editMovie(@PathVariable long id, @Valid @RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.editMovie(id, movieDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }
}
