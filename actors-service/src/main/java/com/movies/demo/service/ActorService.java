package com.movies.demo.service;

import java.util.List;
import java.util.Optional;

import com.movies.demo.entity.Actor;

public interface ActorService {

	public void deleteActor(Long id);

	public Actor updateActor(Long id, Actor actorDetails);

	public Actor createActor(Actor actor);

   public Optional<Actor> getActorById(Long id);

	public List<Actor> getAllActors();

}
