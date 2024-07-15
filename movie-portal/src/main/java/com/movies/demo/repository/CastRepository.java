package com.movies.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movies.demo.entity.Cast;

public interface CastRepository extends JpaRepository<Cast, Long> {

}
