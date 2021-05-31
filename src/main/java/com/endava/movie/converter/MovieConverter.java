package com.endava.movie.converter;

import com.endava.movie.model.Movie;
import com.endava.movie.model.MovieDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieConverter {
    public MovieDTO modelToDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setName(movie.getName());
        movieDTO.setBudget(movie.getBudget());
        movieDTO.setDescription(movie.getDescription());

        return movieDTO;
    }

    public List<MovieDTO> modelToDTO(List<Movie> movies) {
        return movies.stream().map(this::modelToDTO).collect(Collectors.toList());
    }

    public Movie dtoToModel(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setName(movieDTO.getName());
        movie.setBudget(movieDTO.getBudget());
        movie.setDescription(movieDTO.getDescription());

        return movie;
    }

    public List<Movie> dtoToModel(List<MovieDTO> dtoMovies) {
        return dtoMovies.stream().map(this::dtoToModel).collect(Collectors.toList());
    }

}
