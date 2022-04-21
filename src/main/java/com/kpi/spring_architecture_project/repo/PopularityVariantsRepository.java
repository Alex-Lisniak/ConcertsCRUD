package com.kpi.spring_architecture_project.repo;


import com.kpi.spring_architecture_project.models.PopularityVariants;
import org.springframework.data.repository.CrudRepository;

public interface PopularityVariantsRepository extends CrudRepository<PopularityVariants, Long> {
}