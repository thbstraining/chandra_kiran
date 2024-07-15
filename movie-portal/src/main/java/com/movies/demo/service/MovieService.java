package com.movies.demo.service;

import java.util.List;

import com.movies.demo.dto.MovieRequestDTO;
import com.movies.demo.entity.Cast;
import com.movies.demo.entity.Movie;

public interface MovieService {

	public void deleteMovie(Long id);

	public Movie updateMovie(Long id, MovieRequestDTO movieRequest);

	public List<Movie> getAllMovies();

	public Movie saveMovie(MovieRequestDTO movieRequest);

	public Movie getMovieById(Long id);
    public void saveCast(Cast cast);


}
