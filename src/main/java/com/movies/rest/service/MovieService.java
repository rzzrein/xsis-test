package com.movies.rest.service;

import com.movies.rest.dto.MovieDto;
import com.movies.rest.entity.Movie;

import java.util.List;

public interface MovieService {
    MovieDto createMovie(MovieDto movieDto);
    List<MovieDto> getAllMovies();
    MovieDto getMovieById(long id);
    MovieDto editMovie(long id, MovieDto movieDto);
    void deleteMovie(long id);
}
