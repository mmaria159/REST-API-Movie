package com.endava.movie.api.restcontroller;

import com.endava.movie.exceptionhandling.DataBaseException;
import com.endava.movie.exceptionhandling.MovieNotFoundException;
import com.endava.movie.model.Movie;
import com.endava.movie.model.MovieDTO;
import com.endava.movie.service.MovieService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RequiredArgsConstructor
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/movies")
public class MovieRestController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<Object> getAllMovies() throws DataBaseException {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) throws MovieNotFoundException {
        return movieService.getMovieById(id);
    }


    @PostMapping
    public ResponseEntity<Object> addMovie(@RequestBody MovieDTO movieDTO){
        movieService.addMovie(movieDTO);
        return new ResponseEntity<>("Movie \"" + movieDTO.getName() + "\" has been added successfully!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) throws MovieNotFoundException {
        movieService.updateMovie(id,movieDTO);
        return new ResponseEntity<>("Movie with id \"" + id + "\" has been updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable Long id) throws DataBaseException, MovieNotFoundException {
        movieService.deleteMovie(id);
        return new ResponseEntity<>("Movie with id \"" + id + "\" has been deleted successfully!", HttpStatus.OK);
    }

}
