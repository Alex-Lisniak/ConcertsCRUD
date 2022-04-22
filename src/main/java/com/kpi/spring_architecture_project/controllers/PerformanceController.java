package com.kpi.spring_architecture_project.controllers;

import com.kpi.spring_architecture_project.models.Actor;
import com.kpi.spring_architecture_project.models.Concert;
import com.kpi.spring_architecture_project.models.Performance;
import com.kpi.spring_architecture_project.repo.ActorRepository;
import com.kpi.spring_architecture_project.repo.ConcertRepository;
import com.kpi.spring_architecture_project.repo.PerformanceRepository;
import com.kpi.spring_architecture_project.services.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PerformanceController {

    @Autowired
    PerformanceService performanceService;



    @GetMapping("/performances")
    public String index(Model model){

        String returningImage = performanceService.index(model);

        return returningImage;
    }

    @GetMapping("/performances/{id}")
    public String concertDetails(@PathVariable(value = "id") long id , Model model){

        String returningImage = performanceService.concertDetails(id, model);

        return returningImage;
    }
}
