package com.napier.sem.group6;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * represents a country
 * Author = Alasdair Fairgrieve
 * Date = 25/02/2020
 */

    //This is the city constructor class
public class City {

    /**
     * city name
     */
    public String name;
    public String country;
    public String district;
    public int population;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }



}

