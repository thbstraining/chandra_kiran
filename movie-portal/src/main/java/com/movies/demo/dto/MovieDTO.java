package com.movies.demo.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@AllArgsConstructor
public class MovieDTO {
    private String title;
    private String director;
    private String genre;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date releaseDate;
	private String description;
    private String language;
    private List<ActorDTO> actors;


    // Constructor, getters, setters omitted for brevity
}

