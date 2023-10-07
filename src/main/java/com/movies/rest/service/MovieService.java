package com.movies.rest.service;

import com.movies.rest.dto.MovieDto;
import com.movies.rest.dto.PagedMovies;
import java.lang.reflect.InvocationTargetException;

public interface MovieService {
    MovieDto createMovie(MovieDto movieDto);
    PagedMovies getAllMovies(int pageNo, int pageSize) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    MovieDto getMovieById(long id);
    MovieDto editMovie(long id, MovieDto movieDto);
    void deleteMovie(long id);
}
