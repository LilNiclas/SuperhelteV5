package com.example.superherov5.controller;

import com.example.superherov5.dto.HeroPowerDTO;
import com.example.superherov5.dto.SuperheroDTO;
import com.example.superherov5.dto.SuperheroFormDTO;
import com.example.superherov5.repository.IRepository;
import com.example.superherov5.service.MyService;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "kea")
public class MyController {

    private MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    //public MyController(ApplicationContext context, @Value("superheroDB") String impl){
      //  myService = (IRepository) context.bean(impl); }


    //Q2
    @GetMapping(path = "superheroes")     //localhost:8083/kea/superheroes
    public String getSuperheroes(Model model) {
        List<SuperheroDTO> getSuperheroes = myService.getSuperheroes();
        model.addAttribute("heroes", getSuperheroes);
        return "index";
    }

    //Q3
    @GetMapping(path = "superpower/{name}")     //localhost:8083/kea/superpower/{name}
    public String powerByName(@PathVariable String name, Model model) {
        HeroPowerDTO powerByName = myService.heroPowerByName(name);
        model.addAttribute("heroPowers", powerByName);
        return "powers";
    }

    //Q4

    @GetMapping(path = "superhero/add")
    public String showCreateHero(Model model){
        SuperheroFormDTO superhero = new SuperheroFormDTO();
        model.addAttribute("superhero", superhero);
        model.addAttribute("cities", myService.getCities());
        model.addAttribute("powers", myService.getPowers());
        return "create";
    }

    @PostMapping(path = "superhero/add")
    public String addHero(@ModelAttribute("superhero") SuperheroFormDTO superheroFormDTO){
        myService.addSuperHero(superheroFormDTO);
        return "redirect:/kea/superheroes";
    }
}
