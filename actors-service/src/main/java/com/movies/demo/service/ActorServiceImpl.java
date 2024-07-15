package com.movies.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movies.demo.entity.Actor;
import com.movies.demo.repository.ActorRepository;

@Service
public class ActorServiceImpl implements ActorService {

	
	@Autowired
    private ActorRepository actorRepository;

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    
    public Optional<Actor> getActorById(Long id) {
        return actorRepository.findById(id);
    }

    public Actor createActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public Actor updateActor(Long id, Actor actorDetails) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actor not found"));

        actor.setActorName(actorDetails.getActorName());
        actor.setAge(actorDetails.getAge());
        actor.setDob(actorDetails.getDob());
        actor.setTotalMovies(actorDetails.getTotalMovies());
        actor.setIndustry(actorDetails.getIndustry());

        return actorRepository.save(actor);
    }

    public void deleteActor(Long id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actor not found"));
        actorRepository.delete(actor);
    }
}
