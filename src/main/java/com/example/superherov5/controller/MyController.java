package com.example.superherov5.controller;

import com.example.superherov5.dto.SuperheroDTO;
import com.example.superherov5.service.MyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
