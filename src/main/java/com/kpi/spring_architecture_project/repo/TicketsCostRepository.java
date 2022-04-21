package com.kpi.spring_architecture_project.repo;

import com.kpi.spring_architecture_project.models.TicketsCost;
import org.springframework.data.repository.CrudRepository;

public interface TicketsCostRepository extends CrudRepository<TicketsCost, Long> {
}