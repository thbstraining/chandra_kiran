package com.movies.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movies.demo.entity.Director;
import com.movies.demo.repository.DirectorRepository;

@Service
public class DirectorServiceImpl implements DirectorService {
	
	 @Autowired
	    private DirectorRepository directorRepository;

	    public List<Director> getAllDirectors() {
	        return directorRepository.findAll();
	    }

	    
	    public Optional<Director> getDirectorById(Long id) {
	        return directorRepository.findById(id);
	    }

	    public Director createDirector(Director director) {
	        return directorRepository.save(director);
	    }

	    public Director updateDirector(Long id, Director directorDetails) {
	        Director director = directorRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Director not found"));

	        director.setDirectorName(directorDetails.getDirectorName());
	        director.setAge(directorDetails.getAge());
	        director.setDob(directorDetails.getDob());
	        director.setTotalMovies(directorDetails.getTotalMovies());
	        director.setIndustry(directorDetails.getIndustry());

	        return directorRepository.save(director);
	    }

	    public void deleteDirector(Long id) {
	        Director director = directorRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Director not found"));
	        directorRepository.delete(director);
	    }

		

}
