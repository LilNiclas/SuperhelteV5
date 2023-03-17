package com.example.superherov5.repository;

import com.example.superherov5.dto.HeroPowerDTO;
import com.example.superherov5.dto.SuperheroDTO;
import com.example.superherov5.model.Superhero;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
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
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd)) {
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
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd)) {
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
}
