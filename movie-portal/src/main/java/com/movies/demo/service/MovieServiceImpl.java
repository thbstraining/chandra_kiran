package com.movies.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movies.demo.dto.CastRequestDTO;
import com.movies.demo.dto.MovieRequestDTO;
import com.movies.demo.entity.Cast;
import com.movies.demo.entity.Movie;
import com.movies.demo.repository.CastRepository;
import com.movies.demo.repository.MovieRepository;

import jakarta.transaction.Transactional;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
    private MovieRepository movieRepository;
	@Autowired
	private CastRepository castRepository;

//    @Transactional
//    public Movie saveMovie(MovieRequestDTO movieRequest) {
//        Movie movie = new Movie();
//        movie.setTitle(movieRequest.getTitle());
//        movie.setDirectorId(movieRequest.getDirectorId());
//        movie.setGenre(movieRequest.getGenre());
//        movie.setReleaseDate(movieRequest.getReleaseDate());
//        movie.setLanguage(movieRequest.getLanguage());
//        movie.setDescription(movieRequest.getDescription());
//
//        // Optionally, add casts to the movie if provided in the request
//        if (movieRequest.getCasts() != null) {
//            for (CastRequestDTO castRequest : movieRequest.getCasts()) {
//                Cast cast = new Cast();
//                cast.setActorId(castRequest.getActorId());
//                cast.setRole(castRequest.getRole());
//                movie.addCast(cast);
//            }
//        }
//
//        return movieRepository.save(movie);
//    }
	    @Transactional
	    public Movie saveMovie(MovieRequestDTO movieRequest) {
	        Movie movie = new Movie();
	        movie.setTitle(movieRequest.getTitle());
	        movie.setDirectorId(movieRequest.getDirectorId());
	        movie.setReleaseDate(movieRequest.getReleaseDate());
	        movie.setGenre(movieRequest.getGenre());
	        movie.setDescription(movieRequest.getDescription());
	        movie.setLanguage(movieRequest.getLanguage());

	        // Save movie
	        Movie savedMovie = movieRepository.save(movie);

	        // Save casts
	        if (movieRequest.getCasts() != null) {
	            for (CastRequestDTO castRequest : movieRequest.getCasts()) {
	                Cast cast = new Cast();
	                cast.setMovie(savedMovie);
	                cast.setActorId(castRequest.getActorId());
	                cast.setRole(castRequest.getRole());
	                savedMovie.addCast(cast); // Add cast to movie entity
	            }
	        }

	        return savedMovie;
	    }

    @Transactional
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Transactional
    public Movie getMovieById(Long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        return optionalMovie.orElse(null);
    }

    @Transactional
    public Movie updateMovie(Long id, MovieRequestDTO movieRequest) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            movie.setTitle(movieRequest.getTitle());
            movie.setDirectorId(movieRequest.getDirectorId());
            movie.setGenre(movieRequest.getGenre());
            movie.setReleaseDate(movieRequest.getReleaseDate());

            // Update casts
            List<Cast> currentCasts = movie.getCasts();
            for (Cast currentCast : currentCasts) {
                currentCast.setMovie(null); // Remove the association temporarily
            }
            currentCasts.clear(); // Clear existing casts

            if (movieRequest.getCasts() != null) {
                for (CastRequestDTO castRequest : movieRequest.getCasts()) {
                    Cast cast = new Cast();
                    cast.setMovie(movie);
                    cast.setActorId(castRequest.getActorId());
                    cast.setRole(castRequest.getRole());
                    movie.addCast(cast);
                }
            }

            return movieRepository.save(movie);
        }
        return null;
    }

    @Transactional
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

	@Override
	public void saveCast(Cast cast) {
		// TODO Auto-generated method stub
		castRepository.save(cast);
	}


	

}
