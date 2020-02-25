package com.napier.sem.group6;

import java.sql.*;
import java.util.ArrayList;

/**
main method
*/
public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
        // Get countries by population DESC from database
        //ArrayList<Country> cnt = a.getCountry();
        // process results from SQL and display results
        //a.printCountry(cnt);
        Continent cont = a.getContinentPopulation("Europe");
        //printContinent
        a.printContinent(cont);

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
     * Gets all countries from the world sorted by population Descending
     * @return A list of all countries or null if there is an error.
     */
    public ArrayList<Country> getCountry()
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
            ArrayList<Country> countries  = new ArrayList<Country>();
            // Check one is returned
            while (rset.next())
            {
                Country cnt = new Country();
                cnt.code = rset.getString("code");
                cnt.name = rset.getString("name");
                cnt.continent = rset.getString("continent");
                cnt.region = rset.getString("region");
                cnt.population = rset.getInt("population");
                cnt.capital = rset.getInt("capital");
                countries.add(cnt);
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

    public Continent getContinentPopulation(String CONT)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "select sum(population), continent from country where continent = '" + CONT + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return population and continent if valid.
            // Check one is returned
            if (rset.next())
            {
                Continent cont = new Continent();
                cont.population = rset.getInt("population");
                cont.continentName = rset.getString("continent");
                return cont;
            } else
                return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }

    }
    /**
     * Prints a list of countries.
     * @param countries The list of countries to print.
     */
            public void printCountry(ArrayList<Country> countries)
            {
                // Print header
                System.out.println(String.format("%-10s %-15s %-20s %-8s %-8s %-8s", "code", "name", "continent", "region", "population", "capital"));
                // Loop over all countries in the list
                for (Country cnt : countries)
                {
                    String country_string =
                            String.format("%-10s %-15s %-20s %-8s %-8s %-8s",
                                    cnt.code, cnt.name, cnt.continent, cnt.region, cnt.population, cnt.capital);
                    System.out.println(country_string);
                }
            }

            public void printContinent(Continent cont){
                System.out.println(String.format("%-10s %-15s", "population", "continent"));
                String continent_string =
                        String.format("%-10s %-15s %-20s %-8s %-8s %-8s",
                                cont.population, cont.continentName);
                System.out.println(continent_string);

            }
    }
