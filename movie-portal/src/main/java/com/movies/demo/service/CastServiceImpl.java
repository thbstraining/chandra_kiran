package com.movies.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movies.demo.dto.CastRequestDTO;
import com.movies.demo.entity.Cast;
import com.movies.demo.repository.CastRepository;

import jakarta.transaction.Transactional;

@Service
public class CastServiceImpl implements CastService {
	
	@Autowired
    private CastRepository castRepository;

    @Transactional
    public Cast saveCast(CastRequestDTO castRequest) {
        Cast cast = new Cast();
        cast.setActorId(castRequest.getActorId());
        cast.setRole(castRequest.getRole());

        // Set the movie for the cast (assuming movieId is provided in the request)
        // You may need to fetch the Movie entity from another microservice if needed
        // cast.setMovie(movieService.getMovieById(castRequest.getMovieId()));

        return castRepository.save(cast);
    }

    @Transactional
    public List<Cast> getAllCasts() {
        return castRepository.findAll();
    }

    @Transactional
    public Cast getCastById(Long id) {
        Optional<Cast> optionalCast = castRepository.findById(id);
        return optionalCast.orElse(null);
    }

    @Transactional
    public Cast updateCast(Long id, CastRequestDTO castRequest) {
        Optional<Cast> optionalCast = castRepository.findById(id);
        if (optionalCast.isPresent()) {
            Cast cast = optionalCast.get();
            cast.setActorId(castRequest.getActorId());
            cast.setRole(castRequest.getRole());

            // Update movie association if needed
            // cast.setMovie(movieService.getMovieById(castRequest.getMovieId()));

            return castRepository.save(cast);
        }
        return null;
    }

    @Transactional
    public void deleteCast(Long id) {
        castRepository.deleteById(id);
    }
    
    public Cast saveCast(Cast cast) {
        return castRepository.save(cast);
    }

}
