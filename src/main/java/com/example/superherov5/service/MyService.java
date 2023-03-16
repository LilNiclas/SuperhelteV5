package com.example.superherov5.service;

import com.example.superherov5.dto.HeroPowerDTO;
import com.example.superherov5.dto.SuperheroDTO;
import com.example.superherov5.model.Superhero;
import com.example.superherov5.repository.MyRepositoryDB;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {

    MyRepositoryDB myRepositorydb;

    public MyService(MyRepositoryDB myRepositorydb) {
        this.myRepositorydb = myRepositorydb;
    }

    //Q2
    public List<SuperheroDTO> getSuperheroes() {
        return myRepositorydb.getSuperheroes();
    }

    //Q3
    public HeroPowerDTO heroPowerByName(String name) {
        return myRepositorydb.heroPowerByName(name);
    }


}
