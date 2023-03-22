package com.example.superherov5.repository;

import com.example.superherov5.dto.HeroPowerDTO;
import com.example.superherov5.dto.SuperheroDTO;
import com.example.superherov5.dto.SuperheroFormDTO;
import com.example.superherov5.model.Superhero;

import java.util.List;

public interface IRepository {

    //Q2:
    public List<SuperheroDTO> getSuperheroes();

    //Q3:
    public HeroPowerDTO heroPowerByName(String name);

    //Q4:
    public List<String> getCities();
    public List<String> getPowers();
    public void addSuperHero(SuperheroFormDTO form);
}
