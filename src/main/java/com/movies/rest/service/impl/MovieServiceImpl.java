package com.movies.rest.service.impl;

import com.movies.rest.dto.MovieDto;
import com.movies.rest.dto.PagedMovies;
import com.movies.rest.entity.Movie;
import com.movies.rest.exception.ResourceNotFoundException;
import com.movies.rest.repository.MovieRepository;
import com.movies.rest.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieDto createMovie(MovieDto movieDto) {
        Movie movie = mapToEntity(movieDto);
        Movie newMovie = movieRepository.save(movie);
        return mapToDto(newMovie);
    }

    @Override
    public PagedMovies getAllMovies(int pageNo, int pageSize) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Movie> movies = movieRepository.findAll(pageable);
        return mapToPagedMovies(movies);
    }

    @Override
    public MovieDto getMovieById(long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
        return mapToDto(movie);
    }

    @Override
    public MovieDto editMovie(long id, MovieDto movieDto) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));

        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setRating(movieDto.getRating());
        movie.setImage(movieDto.getImage());

        Movie newMovie = movieRepository.save(movie);
        return mapToDto(newMovie);
    }

    @Override
    public void deleteMovie(long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
        movieRepository.delete(movie);
    }

    private MovieDto mapToDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        movieDto.setRating(movie.getRating());
        movieDto.setImage(movie.getImage());
        movieDto.setCreated_at(movie.getCreatedAt());
        movieDto.setUpdated_at(movie.getUpdatedAt());
        return movieDto;
    }

    private Movie mapToEntity(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setRating(movieDto.getRating());
        movie.setImage(movieDto.getImage());
        return movie;
    }

    private PagedMovies mapToPagedMovies(Page<Movie> movies) {
        List<Movie> moviesList = movies.getContent();
        List<MovieDto> content =  moviesList.stream().map(this::mapToDto).toList();
        PagedMovies pagedMovies = new PagedMovies();
        pagedMovies.setContent(content);
        pagedMovies.setPageNo(movies.getNumber());
        pagedMovies.setPageSize(movies.getSize());
        pagedMovies.setTotalElements(movies.getTotalElements());
        pagedMovies.setTotalPages(movies.getTotalPages());
        pagedMovies.setLast(movies.isLast());
        return pagedMovies;
    }
}
