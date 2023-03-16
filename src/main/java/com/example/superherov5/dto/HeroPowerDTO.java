package com.example.superherov5.dto;

public class HeroPowerDTO {

    private String heroName;
    private String realName;
    private String superpowers;

    public HeroPowerDTO(String heroName, String realName, String superpowers) {
        this.heroName = heroName;
        this.realName = realName;
        this.superpowers = superpowers;
    }

    public String getHeroName() {
        return heroName;
    }

    public String getRealName() {
        return realName;
    }

    public String getSuperpowers() {
        return superpowers;
    }
}
