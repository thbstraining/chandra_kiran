package com.movies.demo.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Data
public class MovieRequestDTO {
	
	private Long movieId ;
	private String title;
    private Long directorId;
    private String genre;

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    private List<CastRequestDTO> casts;
    
    private String description;
    private String language;
    private List<ActorDTO> actors;
}
