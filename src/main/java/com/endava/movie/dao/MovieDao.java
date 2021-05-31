package com.endava.movie.dao;

import com.endava.movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDao extends JpaRepository<Movie, Long> {
}
