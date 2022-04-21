package com.kpi.spring_architecture_project.repo;


import com.kpi.spring_architecture_project.models.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor, Long> {
}
