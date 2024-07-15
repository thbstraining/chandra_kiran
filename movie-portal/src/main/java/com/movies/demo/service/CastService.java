package com.movies.demo.service;

import java.util.List;

import com.movies.demo.dto.CastRequestDTO;
import com.movies.demo.entity.Cast;

public interface CastService {

	public void deleteCast(Long id);

	public Cast updateCast(Long id, CastRequestDTO castRequest);

	public Cast getCastById(Long id);

	public List<Cast> getAllCasts();

	public Cast saveCast(CastRequestDTO castRequest);
	
	//public Cast saveCastData(Cast cast);

}
