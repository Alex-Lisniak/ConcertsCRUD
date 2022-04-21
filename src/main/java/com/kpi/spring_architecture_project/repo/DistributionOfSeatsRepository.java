package com.kpi.spring_architecture_project.repo;


import com.kpi.spring_architecture_project.models.DistributionOfSeats;
import org.springframework.data.repository.CrudRepository;

public interface DistributionOfSeatsRepository extends CrudRepository<DistributionOfSeats, Long> {
}