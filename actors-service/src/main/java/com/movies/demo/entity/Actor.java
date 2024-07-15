package com.movies.demo.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Entity
public class Actor {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="actor_id")
	private Long actorId;
	private String actorName;
	private Long age;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dob;
	private Long totalMovies;
	private String industry;
	

}
