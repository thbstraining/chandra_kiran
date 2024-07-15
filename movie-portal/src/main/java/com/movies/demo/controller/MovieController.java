//package com.movies.demo.controller;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.movies.demo.dto.ActorDTO;
//import com.movies.demo.dto.CastDTO;
//import com.movies.demo.dto.MovieDTO;
//import com.movies.demo.dto.MovieRequestDTO;
//import com.movies.demo.entity.Cast;
//import com.movies.demo.entity.Movie;
//import com.movies.demo.service.MovieService;
//
//@RestController
//@RequestMapping("/api/movies")
//public class MovieController {
//
//	
//	@Autowired
//    private RestTemplate restTemplate;
//    @Autowired
//    private MovieService movieService;
//
//    // Create a new movie
//    @PostMapping
//    public ResponseEntity<Movie> createMovie(@RequestBody MovieRequestDTO movieRequest) {
//        Movie savedMovie = movieService.saveMovie(movieRequest);
//        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
//    }
//
//    // Fetch all movies
//    @GetMapping
//    public ResponseEntity<List<Movie>> getAllMovies() {
//        List<Movie> movies = movieService.getAllMovies();
//        return new ResponseEntity<>(movies, HttpStatus.OK);
//    }
//
//    // Fetch a movie by its ID
////    @GetMapping("/{id}")
////    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
////        Movie movie = movieService.getMovieById(id);
////        return ResponseEntity.ok(movie);
////    }
////    @GetMapping("/{id}")
////    public ResponseEntity<MovieDTO> getMovieDetails(@PathVariable Long id) {
////        Movie movie = movieService.getMovieById(id); // Assuming movieService is injected
////        if (movie == null) {
////            return ResponseEntity.notFound().build();
////        }
////
////        // Fetch director name from another microservice
////        String directorName = fetchDirectorName(movie.getDirectorId());
////
////        // Create a DTO to send back to the client
////        MovieDTO movieDTO = new MovieDTO(movie.getTitle(), directorName, movie.getGenre(), movie.getReleaseDate(),movie.getDescription(),movie.getLanguage());
////
////        return ResponseEntity.ok(movieDTO);
////    }
////
////    private String fetchDirectorName(Long directorId) {
////        // Make an HTTP GET request to Director microservice
////        String directorServiceUrl = "http://localhost:8093/api/directors/{id}";
////        ResponseEntity<String> response = restTemplate.getForEntity(directorServiceUrl, String.class, directorId);
////        
////        // Parse the JSON response to extract directorName
////        ObjectMapper mapper = new ObjectMapper();
////        try {
////            JsonNode root = mapper.readTree(response.getBody());
////            String directorName = root.path("directorName").asText();
////            return directorName;
////        } catch (IOException e) {
////            // Handle exception as needed
////            e.printStackTrace();
////            return ""; // Or handle error response appropriately
////        }
////    }
//   // ------------------------
//    @GetMapping("/{id}")
//    public ResponseEntity<MovieDTO> getMovieDetails(@PathVariable Long id) {
//        Movie movie = movieService.getMovieById(id); // Assuming movieService is injected
//        if (movie == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // Fetch director name from another microservice
//        String directorName = fetchDirectorName(movie.getDirectorId());
//        
//
//        // Fetch actors from another microservice
//        List<ActorDTO> actors = fetchActorsByCast(movie.getCasts());
//     // Create MovieDTO to send back to client
//        MovieDTO movieDTO = new MovieDTO(
//            movie.getTitle(),
//            directorName,
//            movie.getGenre(),
//            movie.getReleaseDate(),
//            movie.getDescription(),
//            movie.getLanguage(),
//            actors
//        );
//
//        // Create a DTO to send back to the client
//       // MovieDTO movieDTO = new MovieDTO(movie.getTitle(), directorName, movie.getGenre(), movie.getReleaseDate(), movie.getDescription(), movie.getLanguage(), actors);
//
//        return ResponseEntity.ok(movieDTO);
//    }
//
//    private String fetchDirectorName(Long directorId) {
//        // Make an HTTP GET request to Director microservice
//        String directorServiceUrl = "http://localhost:8093/api/directors/{id}";
//        ResponseEntity<String> response = restTemplate.getForEntity(directorServiceUrl, String.class, directorId);
//
//        // Parse the JSON response to extract directorName
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            JsonNode root = mapper.readTree(response.getBody());
//            String directorName = root.path("directorName").asText();
//            return directorName;
//        } catch (IOException e) {
//            // Handle exception as needed
//            e.printStackTrace();
//            return ""; // Or handle error response appropriately
//        }
//    }
//
//    private List<ActorDTO> fetchActorsByCast(List<Cast> casts) {
//        List<ActorDTO> actors = new ArrayList<>();
//        for (Cast cast : casts) {
//            ActorDTO actor = fetchActorById(cast.getActorId());
//            if (actor != null) {
//                // Set role to actor DTO if needed
//                actor.setRole(cast.getRole());
//                actors.add(actor);
//            }
//        }
//        return actors;
//    }
//
//
//    private ActorDTO fetchActorById(Long actorId) {
//        // Make an HTTP GET request to Actor microservice
//        String actorServiceUrl = "http://localhost:8093/api/actors/{id}";
//        ResponseEntity<String> response = restTemplate.getForEntity(actorServiceUrl, String.class, actorId);
//
//        // Parse the JSON response to extract actor details
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            return mapper.readValue(response.getBody(), ActorDTO.class);
//        } catch (IOException e) {
//            // Handle exception as needed
//            e.printStackTrace();
//            return null; // Or handle error response appropriately
//        }
//    }
//
//    // Update a movie
//    @PutMapping("/{id}")
//    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody MovieRequestDTO movieRequest) {
//        Movie updatedMovie = movieService.updateMovie(id, movieRequest);
//        return ResponseEntity.ok(updatedMovie);
//    }
//
//    // Delete a movie
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
//        movieService.deleteMovie(id);
//        return ResponseEntity.noContent().build();
//    }
//}

// -***************** controller logic below ***************************************************************///
package com.movies.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.demo.dto.ActorDTO;
import com.movies.demo.dto.CastDTO;
import com.movies.demo.dto.CastFormDTO;
import com.movies.demo.dto.MovieDTO;
import com.movies.demo.dto.MovieRequestDTO;
import com.movies.demo.entity.Cast;
import com.movies.demo.entity.Movie;
import com.movies.demo.service.CastService;
import com.movies.demo.service.MovieService;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieService movieService;
    

    // Get all movies  working
    @GetMapping("/all")
    public String getAllMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "movies-list";
    }

    // Get movie details by ID
    @GetMapping("/{id}")
    public String getMovieDetails(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        if (movie == null) {
            return "redirect:/movies/";
        }

        String directorName = fetchDirectorName(movie.getDirectorId());
        List<ActorDTO> actors = fetchActorsByCast(movie.getCasts());

        MovieDTO movieDTO = new MovieDTO(
            movie.getTitle(),
            directorName,
            movie.getGenre(),
            movie.getReleaseDate(),
            movie.getDescription(),
            movie.getLanguage(),
            actors
        );

        model.addAttribute("movie", movieDTO);
        return "movie-details";
    }

    // Create a new movie working
    @GetMapping("/new")
    public String showMovieForm(Model model) {
        model.addAttribute("movieRequest", new MovieRequestDTO());
        return "movie-form";
    }
    
    // Process creation of a new movie
    @PostMapping("/new")
    public String createMovie(@ModelAttribute("movieRequest") MovieRequestDTO movieRequest) {
        // Save movie details
        Movie savedMovie = movieService.saveMovie(movieRequest);

        // Save cast details
        if (movieRequest.getActors() != null) {
            for (ActorDTO actor : movieRequest.getActors()) {
                Cast cast = new Cast();
                cast.setMovie(savedMovie); // Associate cast with the saved movie
                cast.setActorId(actor.getActorId());
                cast.setRole(actor.getRole());
                movieService.saveCast(cast); // Save cast entry using movieService
            }
        }

        return "redirect:/movies/all"; // Redirect to movie list after successful creation
    }


    // Update a movie 
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        if (movie == null) {
            return "redirect:/movies/all";
        }

        model.addAttribute("movieRequest", movie); // Assuming MovieRequestDTO has setters for these fields
        return "update-form";
    }

    @PostMapping("/edit/{id}")
    public String updateMovie(@PathVariable("id") Long id, @ModelAttribute("movieRequest") MovieRequestDTO movieRequest) {
        movieService.updateMovie(id, movieRequest);
        return "redirect:/movies/all";
    }

    // Delete a movie working
    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteMovie(id);
        return "redirect:/movies/all";
    }

    // Helper methods
    private String fetchDirectorName(Long directorId) {
        String directorServiceUrl = "http://localhost:8093/api/directors/{id}";
        ResponseEntity<String> response = restTemplate.getForEntity(directorServiceUrl, String.class, directorId);

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response.getBody());
            return root.path("directorName").asText();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private List<ActorDTO> fetchActorsByCast(List<Cast> casts) {
        List<ActorDTO> actors = new ArrayList<>();
        for (Cast cast : casts) {
            ActorDTO actor = fetchActorById(cast.getActorId());
            if (actor != null) {
                actor.setRole(cast.getRole());
                actors.add(actor);
            }
        }
        return actors;
    }

    private ActorDTO fetchActorById(Long actorId) {
        String actorServiceUrl = "http://localhost:8093/api/actors/{id}";
        ResponseEntity<String> response = restTemplate.getForEntity(actorServiceUrl, String.class, actorId);

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(response.getBody(), ActorDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @GetMapping("/add-cast/{id}")
    public String showAddCastForm(@PathVariable("id") Long movieId, Model model) {
        CastFormDTO castForm = new CastFormDTO();
        castForm.setMovieId(movieId);
        model.addAttribute("castForm", castForm);
        return "add-cast"; // Thymeleaf template name for the add cast form
    }

    @PostMapping("/save-cast")
    public String saveCast(@ModelAttribute("castForm") CastFormDTO castForm) {
        Cast cast = new Cast();
        Movie movie = movieService.getMovieById(castForm.getMovieId());

        if (movie == null) {
            // Handle case where movieId is invalid or not found
            return "redirect:/error"; // Redirect to an error page or handle appropriately
        }

        cast.setMovie(movie);
        cast.setActorId(castForm.getActorId());
        cast.setRole(castForm.getRole());

        movieService.saveCast(cast);

        return "redirect:/movies/" + castForm.getMovieId(); // Redirect to movie details page
    }
}

