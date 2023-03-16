package com.example.superherov5.controller;

import com.example.superherov5.dto.HeroPowerDTO;
import com.example.superherov5.dto.SuperheroDTO;
import com.example.superherov5.service.MyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "kea")
public class MyController {

    private MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    //Q1
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

}
