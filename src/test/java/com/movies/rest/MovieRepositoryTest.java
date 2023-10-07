package com.movies.rest;

import com.movies.rest.entity.Movie;
import com.movies.rest.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieRepositoryTest {
    private final MovieRepository movieRepository = Mockito.mock(MovieRepository.class);

    @Test
    void saveReturnsMovie() {
        Movie movie = new Movie();
        Mockito.when(movieRepository.save(any(Movie.class))).thenReturn(movie);
        Movie newMovie = movieRepository.save(movie);
        assertEquals(movie, newMovie);
    }

    @Test
    void findAllReturnsMovies() {
        List<Movie> movies = new ArrayList<>();
        Mockito.when(movieRepository.findAll()).thenReturn(movies);
        List<Movie> moviesList = movieRepository.findAll();
        assertEquals(movies, moviesList);
    }

    @Test
    void findByIdReturnsMovie() {
        Movie movie = new Movie();
        Mockito.when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movie));
        Optional<Movie> movieById = movieRepository.findById((long)1);
        assertEquals(movie, movieById.get());
    }

    @Test
    void updateMovieReturnsMovie() {
        Movie movie = new Movie();
        Mockito.when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movie));
        Mockito.when(movieRepository.save(any(Movie.class))).thenReturn(movie);
        Optional<Movie> oldMovie = movieRepository.findById((long)1);
        Movie newMovie = movieRepository.save(oldMovie.get());
        assertEquals(oldMovie.get(), newMovie);
    }

    @Test
    void deleteMovieExecuted() {
        Movie movie = new Movie();
        Mockito.when(movieRepository.findById((long)1)).thenReturn(Optional.of(movie));
        Mockito.doNothing().when(movieRepository).delete(movie);
        Optional<Movie> movieToDelete = movieRepository.findById((long)1);
        movieRepository.delete(movieToDelete.get());
        verify(movieRepository, times(1)).delete(movie);
    }
}
