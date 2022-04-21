package com.kpi.spring_architecture_project.controllers;


import com.kpi.spring_architecture_project.services.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ConcertController {

    @Autowired
    ConcertService concertService;


    @GetMapping("/concerts")
    public String index(Model model){

        String returningImage = concertService.index(model);

        return returningImage;
    }

    @GetMapping("/concerts/add")
    public String concertAdd(Model model){

        return "concertAdd";
    }

    @PostMapping("/concerts/add")
    public String concertPostAdd(@RequestParam String place, @RequestParam String designation,
                                 @RequestParam String performanceName, @RequestParam String distributionOfSeats,
                                 @RequestParam Long countOfSeats, @RequestParam Long costOfTicket,
                                 Model model){

        String returningImage = concertService.addConcert(place, designation,
                                                            performanceName, distributionOfSeats,
                                                            countOfSeats , costOfTicket , model);

        return returningImage;
    }


    @PostMapping("/concerts/addWithNewPerformance")
    public String concertPostAddWithNewPerformance(@RequestParam String place, @RequestParam String designation,
                                                   @RequestParam String distributionOfSeats, @RequestParam Long countOfSeats,
                                                   @RequestParam Long costOfTicket, @RequestParam String performanceName,
                                                   @RequestParam String actorName, Model model){

        String returningImage = concertService.concertPostAddWithNewPerformance(place, designation,
                                                        distributionOfSeats,
                                                        countOfSeats, costOfTicket, performanceName,
                                                        actorName , model);

        return returningImage;
    }


    @GetMapping("/concerts/{id}")
    public String concertDetails(@PathVariable(value = "id") long id , Model model){

        String returningImage  = concertService.cocnertDetails(id , model);

        return returningImage;
    }

    @GetMapping("/concerts/{id}/edit")
    public String concertEdit(@PathVariable(value = "id") long id , Model model){

        String returningImage = concertService.concertEdit(id , model);
        return returningImage;
    }


    @PostMapping("/concerts/{id}/edit")
    public String concertPostEdit(@PathVariable(value = "id") long id ,@RequestParam String place, @RequestParam String designation,
                                  @RequestParam String distributionOfSeats, @RequestParam Long countOfSeats,
                                  @RequestParam Long costOfTicket, Model model){

        String returningImage = concertService.concertPostEdit(id, place, designation,
                                                                distributionOfSeats,
                                                                countOfSeats, costOfTicket, model);
        return returningImage;
    }

    @PostMapping("/concerts/{id}/remove")
    public String concertPostDelete(@PathVariable(value = "id") long id , Model model){

        String returningImage = concertService.concertPostDelete(id , model);

        return returningImage;
    }

}
