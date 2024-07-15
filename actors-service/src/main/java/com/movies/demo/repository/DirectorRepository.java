package com.movies.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movies.demo.entity.Director;

public interface DirectorRepository extends JpaRepository<Director, Long> {

}
