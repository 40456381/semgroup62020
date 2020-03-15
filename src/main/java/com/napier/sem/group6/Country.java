package com.napier.sem.group6;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * represents a country
 * Author = Clark Bolan
 * Date = 04/02/2020
 */

public class Country {

    /**
     * country name
     */
    public String code;
    public String name;
    public String continent;
    public String region;
    public int population;
    public int capital;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }
}

