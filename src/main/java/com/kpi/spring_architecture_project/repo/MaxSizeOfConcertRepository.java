package com.kpi.spring_architecture_project.repo;

import com.kpi.spring_architecture_project.models.MaxSizeOfConcert;
import org.springframework.data.repository.CrudRepository;


public interface MaxSizeOfConcertRepository extends CrudRepository<MaxSizeOfConcert, Long> {
}