package com.example.superherov5.dto;

import java.util.List;

public class SuperheroFormDTO {

    private int heroID;
    private String heroName;
    private String realName;
    private int creationYear;
    private String city;
    private List<String> powers;

    public SuperheroFormDTO() {
    } //Default constructor

    public SuperheroFormDTO(int heroID, String heroName, String realName, int creationYear, String city, List<String> powers) {
        this.heroID = heroID;
        this.heroName = heroName;
        this.realName = realName;
        this.creationYear = creationYear;
        this.city = city;
        this.powers = powers;
    }

    public void add(String power) {
        powers.add(power);
    }

    public int getHeroID() {
        return heroID;
    }

    public void setHeroID(int heroID) {
        this.heroID = heroID;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getPowers() {
        return powers;
    }

    public void setPowers(List<String> powers) {
        this.powers = powers;
    }


}
