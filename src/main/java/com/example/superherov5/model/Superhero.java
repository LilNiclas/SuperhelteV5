package com.example.superherov5.model;

public class Superhero {
    private int heroID;
    private String heroName;
    private String realName;
    private int creationYear;
    private String cityID;

    public Superhero(int heroID, String heroName, String realName, int creationYear, String cityID) {
        this.heroID = heroID;
        this.heroName = heroName;
        this.realName = realName;
        this.creationYear = creationYear;
        this.cityID = cityID;
    }

    public int getHeroID() {
        return heroID;
    }

    public String getHeroName() {
        return heroName;
    }

    public String getRealName() {
        return realName;
    }

    public int getCreationYear() {
        return creationYear;
    }

    public String getCityID() {
        return cityID;
    }
}
