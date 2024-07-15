package com.movies.demo.service;

import java.util.List;
import java.util.Optional;

import com.movies.demo.entity.Director;

public interface DirectorService {

	public void deleteDirector(Long id);

	public Director createDirector(Director director);

	public Director updateDirector(Long id, Director directorDetails);

	//public Object getDirectorById(Long id);
    public Optional<Director> getDirectorById(Long id);


	public List<Director> getAllDirectors();

}
