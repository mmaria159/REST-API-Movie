package com.movie.service;

import com.movie.converter.MovieConverter;
import com.movie.dao.MovieDao;
import com.movie.exceptionhandling.DataBaseException;
import com.movie.exceptionhandling.MovieNotFoundException;
import com.movie.model.Movie;
import com.movie.model.MovieDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
@Data
@RequiredArgsConstructor
public class MovieService {

    private final MovieDao movieDao;
    private final MovieConverter converter;

    public Movie getMovieById(Long id) throws MovieNotFoundException {
        Optional<Movie> movie;
        movie = movieDao.findById(id);
        isPresent(movie);

        return movie.get();
    }

    public List<MovieDTO> getAllMovies() throws DataBaseException {
        List<Movie> movies;
        try {
            movies = new ArrayList<>(movieDao.findAll());
        } catch (Exception e) {
            throw new DataBaseException("Data base issue!", INTERNAL_SERVER_ERROR);
        }
        return converter.modelToDTO(movies);
    }

    public void addMovie(MovieDTO movieDTO) {
        validateMovie(movieDTO);
        movieDao.save(converter.dtoToModel(movieDTO));
    }

    public void updateMovie(Long id, MovieDTO movieDTO) throws MovieNotFoundException {
        Optional<Movie> receivedMovie = movieDao.findById(id);
        isPresent(receivedMovie);
        validateMovie(movieDTO);
        Movie toBeUpdated = receivedMovie.get();
        toBeUpdated.setDescription(movieDTO.getDescription());
        toBeUpdated.setBudget(movieDTO.getBudget());
        toBeUpdated.setName(movieDTO.getName());

        movieDao.save(toBeUpdated);
    }

    public void deleteMovie(Long id) throws DataBaseException, MovieNotFoundException {
        Optional<Movie> movie = movieDao.findById(id);
        isPresent(movie);
        try {
            movieDao.deleteById(id);
        } catch (Exception e) {
            throw new DataBaseException("Data Source issue, could not delete movie", INTERNAL_SERVER_ERROR);
        }
    }

    private void isPresent(Optional<Movie> optionalMovie) throws MovieNotFoundException {
        if (optionalMovie.isPresent()) {
            return;
        }
        throw new MovieNotFoundException("Movie not found!", INTERNAL_SERVER_ERROR);
    }

    private void validateMovie(MovieDTO movieDTO) throws IllegalArgumentException{
        validateString(movieDTO.getName(), "You are trying to set invalid value for movie name!");
        validateString(movieDTO.getDescription(), "You are trying to set invalid value for movie genre!");
        validateBudget(movieDTO.getBudget());
    }

    private void validateString(String string, String errorMessage) throws IllegalArgumentException{
        if (string.isEmpty() || string.contains(" ") || string.length() <= 1 || string.matches(".*\\d.*"))
            throw new IllegalArgumentException(errorMessage);
    }

    private void validateBudget(Double budget)throws IllegalArgumentException{
        if (budget > 10000) {
            return;
        }
        throw new IllegalArgumentException("You are trying to set invalid value for budget!");
    }

}
