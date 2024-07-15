package com.movies.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movies.demo.dto.CastFormDTO;
import com.movies.demo.dto.CastRequestDTO;
import com.movies.demo.entity.Cast;
import com.movies.demo.entity.Movie;
import com.movies.demo.service.CastService;
import com.movies.demo.service.MovieService;


@Controller
@RequestMapping("/api/casts")
public class CastController {

    @Autowired
    private CastService castService;
    private final MovieService movieService;

    @Autowired
    public CastController(MovieService movieService) {
        this.movieService = movieService;
    }

//    // Create a new cast
//    @PostMapping
//    public ResponseEntity<Cast> createCast(@RequestBody CastRequestDTO castRequest) {
//        Cast savedCast = castService.saveCast(castRequest);
//        return new ResponseEntity<>(savedCast, HttpStatus.CREATED);
//    }
//    @GetMapping("/add-cast/{id}")
//    public String showAddCastForm(@PathVariable("id") Long movieId, Model model) {
//        // Prepare a new CastFormDTO and add it to the model
//        CastFormDTO castForm = new CastFormDTO();
//        castForm.setMovieId(movieId); // Set the movieId in the form
//        model.addAttribute("castForm", castForm);
//
//        // Add any other attributes needed for the form or view
//
//        return "add-cast"; // Thymeleaf template name for the add cast form
//    }
//    @PostMapping("/movies/save-cast")
//    public String saveCast(@ModelAttribute("castForm") CastFormDTO castForm) {
//        // Create a new Cast object
//        Cast cast = new Cast();
//        
//        // Retrieve the Movie entity from the database using movieId
//        Movie movie = movieService.getMovieById(castForm.getMovieId());
//        
//        // Set movie and other details to the cast object
//        cast.setMovie(movie);
//        cast.setActorId(castForm.getActorId());
//        cast.setRole(castForm.getRole());
//        
//        // Save the cast using the MovieService (example service method)
//        movieService.saveCast(cast);
//        
//        // Redirect to a confirmation page or back to movie details page
//        return "redirect:/movies/" + castForm.getMovieId(); // Redirect to movie details page
//    }

    // Fetch all casts
    @GetMapping
    public ResponseEntity<List<Cast>> getAllCasts() {
        List<Cast> casts = castService.getAllCasts();
        return new ResponseEntity<>(casts, HttpStatus.OK);
    }

    // Fetch a cast by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Cast> getCastById(@PathVariable Long id) {
        Cast cast = castService.getCastById(id);
        return ResponseEntity.ok(cast);
    }

    // Update a cast
    @PutMapping("/{id}")
    public ResponseEntity<Cast> updateCast(@PathVariable Long id, @RequestBody CastRequestDTO castRequest) {
        Cast updatedCast = castService.updateCast(id, castRequest);
        return ResponseEntity.ok(updatedCast);
    }

    // Delete a cast
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCast(@PathVariable Long id) {
        castService.deleteCast(id);
        return ResponseEntity.noContent().build();
    }
}
