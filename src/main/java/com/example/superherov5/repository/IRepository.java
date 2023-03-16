package com.example.superherov5.repository;

import com.example.superherov5.dto.SuperheroDTO;
import com.example.superherov5.model.Superhero;

import java.util.List;

public interface IRepository {

    //Q1:
    public List<SuperheroDTO> getSuperheroes();
}
