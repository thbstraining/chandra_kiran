package com.movies.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movies.demo.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}
