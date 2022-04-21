package com.kpi.spring_architecture_project.controllers;

import com.kpi.spring_architecture_project.models.Actor;
import com.kpi.spring_architecture_project.models.Concert;
import com.kpi.spring_architecture_project.models.Performance;
import com.kpi.spring_architecture_project.repo.ActorRepository;
import com.kpi.spring_architecture_project.repo.ConcertRepository;
import com.kpi.spring_architecture_project.repo.PerformanceRepository;
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
    PerformanceRepository performanceRepository;
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    ConcertRepository concertRepository;

    @GetMapping("/performances")
    public String index(Model model){
        Iterable<Performance> performances = performanceRepository.findAll();

        model.addAttribute("performances" , performances);

        return "indexPerformances";
    }

    @GetMapping("/performances/{id}")
    public String concertDetails(@PathVariable(value = "id") long id , Model model){
        if(!performanceRepository.existsById(id)){
            return "redirect:/performances";
        }

        Optional<Performance> performance = performanceRepository.findById(id);

        ArrayList<Performance> listOfPerformances = new ArrayList<>();
        performance.ifPresent(listOfPerformances::add);


        Optional<Actor> actor = actorRepository.findById(listOfPerformances.get(0).getId_actors());

        ArrayList<Actor> listOfActors = new ArrayList<>();
        actor.ifPresent(listOfActors::add);

        Optional<Concert> concert = concertRepository.findById(listOfPerformances.get(0).getId_concert());

        ArrayList<Concert> listOfConcerts = new ArrayList<>();
        concert.ifPresent(listOfConcerts::add);


        model.addAttribute("performance" , listOfPerformances.get(0));
        model.addAttribute("actor" , listOfActors.get(0));
        model.addAttribute("concert" , listOfConcerts.get(0));




        performanceRepository.save(listOfPerformances.get(0));
        actorRepository.save(listOfActors.get(0));
        concertRepository.save(listOfConcerts.get(0));
        return "performanceDetails";
    }
}
