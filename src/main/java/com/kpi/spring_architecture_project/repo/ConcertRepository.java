package com.kpi.spring_architecture_project.repo;

import com.kpi.spring_architecture_project.models.Concert;
import org.springframework.data.repository.CrudRepository;

public interface ConcertRepository extends CrudRepository<Concert, Long> {
}
