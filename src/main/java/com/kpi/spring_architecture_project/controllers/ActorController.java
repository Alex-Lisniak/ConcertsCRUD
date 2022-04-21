package com.kpi.spring_architecture_project.controllers;

import com.kpi.spring_architecture_project.models.Actor;
import com.kpi.spring_architecture_project.models.ActorAchievement;
import com.kpi.spring_architecture_project.repo.ActorAchievementRepository;
import com.kpi.spring_architecture_project.repo.ActorRepository;
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
    ActorRepository actorRepository;

    @Autowired
    ActorAchievementRepository actorAchievementRepository;

    @GetMapping("/actors")
    public String index(Model model){
        Iterable<Actor> actors = actorRepository.findAll();

        model.addAttribute("actors" , actors);

        return "indexActors";
    }

    @GetMapping("/actors/{id}")
    public String actorsDetails(@PathVariable(value = "id") long id , Model model){
        if(!actorRepository.existsById(id)){
            return "redirect:/actors";
        }

        Optional<Actor> actor = actorRepository.findById(id);
        ArrayList<Actor> listOfActors = new ArrayList<>();
        actor.ifPresent(listOfActors::add);

        Iterable<ActorAchievement> actorAchievements = actorAchievementRepository.findAll();
        Iterator iterator = actorAchievements.iterator();
        ActorAchievement actorAchievement = null;
        while(iterator.hasNext()){
            ActorAchievement actorAchievementForIterations = (ActorAchievement) iterator.next();
            if(actorAchievementForIterations.getId_actors() == id) {
                actorAchievement = actorAchievementForIterations;
                break;
            }
        }


        model.addAttribute("actor" , listOfActors.get(0));
        model.addAttribute("achievement" , actorAchievement);

        actorRepository.save(listOfActors.get(0));
        actorAchievementRepository.save(actorAchievement);

        return "actorDetails";
    }

}
