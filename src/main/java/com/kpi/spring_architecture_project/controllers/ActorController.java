package com.kpi.spring_architecture_project.controllers;

import com.kpi.spring_architecture_project.models.Actor;
import com.kpi.spring_architecture_project.models.ActorAchievement;
import com.kpi.spring_architecture_project.repo.ActorAchievementRepository;
import com.kpi.spring_architecture_project.repo.ActorRepository;
import com.kpi.spring_architecture_project.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Controller
public class ActorController {

    @Autowired
    ActorService actorService;

    @GetMapping("/actors")
    public String index(Model model){

        String returningImage = actorService.index(model);

        return returningImage;
    }

    @GetMapping("/actors/{id}")
    public String actorsDetails(@PathVariable(value = "id") long id , Model model){

        String returningImage = actorService.actorsDetails(id, model);
        return returningImage;
    }

}
