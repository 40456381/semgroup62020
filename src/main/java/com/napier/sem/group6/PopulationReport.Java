package com.napier.sem.group6;

import java.sql.*;
import java.util.ArrayList;

/**
 main method
 */
public class PopulationReport
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new PopulationReport();

        // Connect to database
        a.connect();
        // Get countries by population DESC from database
        //ArrayList<Country> cnt = a.getCountry();
        // process results from SQL and display results
        //a.printCountry(cnt);
        City cont = a.getCityPopulation();
        //printContinent
        a.printCity(cont);

        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }
    /**
     * Population of a city
     * @return A population of cities or null if there is an error.
     */
    public ArrayList<City> getPopulation()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT code, name, continent, region, population, capital FROM country ORDER BY Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            ArrayList<City> city  = new ArrayList<City>();
            // Check one is returned
            while (rset.next())
            {
                City cnt = new City();
                cnt.name = rset.getString("name");
                cnt.country = rset.getString("country");
                cnt.district = rset.getString("district");
                cnt.population = rset.getInt("population");
                city.add(cnt);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }

    }

    /**
     * Gets the population of a continent
     * @return A Continent or null if there is a error.
     */
    public City getCityPopulation(String CONT)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "select name, country, district, population from city";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return population and continent if valid.
            // Check one is returned
            if (rset.next())
            {
                Continent cont = new Continent();
                cont.population = rset.getInt(1);
                cont.continentName = rset.getString("city");
                return cont;
            } else
                return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }

    }
    /**
     * Prints a list of cities.
     * @param countries The list of cities to print.
     */
    public void printCity(ArrayList<City> city)
    {
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s %-8s %-8s %-8s", "name", "country", "district", "population"));
        // Loop over all countries in the list
        for (City cnt : city)
        {
            String country_string =
                    String.format("%-10s %-15s %-20s %-8s %-8s %-8s",
                            cnt.name, cnt.country, cnt.district, cnt.population);
            System.out.println(city_string);
        }
    }

    /**
     * Prints population of inputted continent.
     * @param cont The continent to print.
     */
    public void printCity(City cont){
        System.out.println(String.format("%-10s %-15s", "population", "continent"));
        String continent_string =
                String.format("%-10s %-15s",
                        cont.population, cont.continentName);
        System.out.println(continent_string);

    }
}
