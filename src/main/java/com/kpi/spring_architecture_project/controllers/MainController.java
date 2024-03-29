package com.kpi.spring_architecture_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("home", "MainPage");
        return "home";
    }
    @GetMapping("/support")
    public String support(Model model) {
        model.addAttribute("support", "SupportPage");
        return "support";
    }

}