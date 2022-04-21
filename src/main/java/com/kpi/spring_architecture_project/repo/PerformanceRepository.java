package com.kpi.spring_architecture_project.repo;

import com.kpi.spring_architecture_project.models.Performance;
import org.springframework.data.repository.CrudRepository;

public interface PerformanceRepository extends CrudRepository<Performance, Long> {
}