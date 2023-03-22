package com.example.superherov5.repository;

import com.example.superherov5.dto.HeroPowerDTO;
import com.example.superherov5.dto.SuperheroDTO;
import com.example.superherov5.dto.SuperheroFormDTO;
import com.example.superherov5.util.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("superheroDB")
public class MyRepositoryDB implements IRepository {

    @Value("${spring.datasource.url}")
    private String db_url;

    @Value("${spring.datasource.username}")
    private String uid;

    @Value("${spring.datasource.password}")
    private String pwd;

    //Q2: Return all heroes
    @Override
    public List<SuperheroDTO> getSuperheroes() {
        List<SuperheroDTO> superheroes = new ArrayList<>();

        try {
            Connection con = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "SELECT HERO_NAME, REAL_NAME, CREATION_YEAR FROM SUPERHEROES";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                String heroName = rs.getString("HERO_NAME");
                String realName = rs.getString("REAL_NAME");
                int creationYear = rs.getInt("CREATION_YEAR");

                superheroes.add(new SuperheroDTO(heroName, realName, creationYear));
            }
            return superheroes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Q3: Return hero and powers
    @Override
    public HeroPowerDTO heroPowerByName(String name) {
        HeroPowerDTO heroPowerObj = null;
        try {
            Connection con = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "SELECT HERO_NAME, REAL_NAME, GROUP_CONCAT(SUPERPOWER SEPARATOR ', ') AS SUPERPOWERS FROM SUPERHEROES \n" +
                    "INNER JOIN SUPERHEROPOWER USING (SUPERHERO_ID)\n" +
                    "INNER JOIN SUPERPOWER USING (SUPERPOWER_ID)\n " +
                    "WHERE HERO_NAME = ? GROUP BY HERO_NAME, REAL_NAME;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String heroName = rs.getString("HERO_NAME");
                String realName = rs.getString("REAL_NAME");
                String superpower = rs.getString("SUPERPOWERS");
                heroPowerObj = new HeroPowerDTO(heroName, realName, superpower);
            }
            return heroPowerObj;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //Q4: Returns cities from City table
    public List<String> getCities() {
        List<String> cities = new ArrayList<>();
        try {
            Connection con = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "SELECT CITY_NAME FROM CITY";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                String cityName = rs.getString("CITY_NAME");
                cities.add(cityName);
            }
            return cities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Q4: Returns superpowers from superpower table
    public List<String> getPowers() {
        List<String> powers = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(db_url, uid, pwd);
            String SQL = "SELECT SUPERPOWER FROM SUPERPOWER";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                String power = rs.getString("SUPERPOWER");
                powers.add(power);
            }
            return powers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Q
    public void addSuperHero(SuperheroFormDTO form) {
        try {
            Connection con = DriverManager.getConnection(db_url, uid, pwd);
            // ID's
            int cityId = 0;
            int heroId = 0;
            List<Integer> powerIDs = new ArrayList<>();

            // find city_id
            String SQL1 = "select city_id from city where city_name = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL1);
            pstmt.setString(1, form.getCity());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                cityId = rs.getInt("city_id");
            }

            // insert row in superhero table
            String SQL2 = "insert into superhero (hero_name, real_name, creation_year, city_id) " +
                    "values(?, ?, ?, ?);";
            pstmt = con.prepareStatement(SQL2, Statement.RETURN_GENERATED_KEYS); // return autoincremented key
            pstmt.setString(1, form.getHeroName());
            pstmt.setString(2, form.getRealName());
            pstmt.setInt(3, form.getCreationYear());
            pstmt.setInt(4, cityId);
            int rows = pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                heroId = rs.getInt(1);
            }

            // find power_ids
            String SQL3 = "select power_id from superpower where superpower = ?;";
            pstmt = con.prepareStatement(SQL3);
            for (String power : form.getPowers()) {
                pstmt.setString(1, power);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    powerIDs.add(rs.getInt("power_id"));
                }

            }

            // insert entries in superhero_powers join table
            String SQL4 = "insert into superheropower values (?,?);";
            pstmt = con.prepareStatement(SQL4);
            for (int i = 0; i < powerIDs.size(); i++) {
                pstmt.setInt(1, heroId);
                pstmt.setInt(2, powerIDs.get(i));
                rows = pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}