package com.movies.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Data
public class Cast {
	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long cast_id;
//	@ManyToOne
//    @JoinColumn(name = "movie_id", nullable = false)
//    private Movie movie_id;
//	private Long actor_id;
//	private String role;
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cast_id")
    private Long castId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    @JsonBackReference
    private Movie movie;

    @Column(name = "actor_id")
    private Long actorId;      // This will store the actor's ID from the Actor microservice

   private String role;
   
}
