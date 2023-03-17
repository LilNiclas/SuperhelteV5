package com.example.superherov5.dto;

public class SuperheroDTO {

     private String heroName;
    private String realName;
    private int creationYear;

    public SuperheroDTO(String heroName, String realName, int creationYear) {
        this.heroName = heroName;
        this.realName = realName;
        this.creationYear = creationYear;
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

}
