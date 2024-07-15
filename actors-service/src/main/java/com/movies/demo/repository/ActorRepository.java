package com.movies.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movies.demo.entity.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {

}
