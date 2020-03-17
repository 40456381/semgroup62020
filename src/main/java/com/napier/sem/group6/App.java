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
        if (args.length < 2)
        {
            a.connect("localhost:33060");
        }
        else{
            a.connect(args[0]);
        }
        // Get countries by population DESC from database
        ArrayList<Country> cnt = a.getCountry();

        // process results from SQL and display results
        a.printCountry(cnt);
        Continent cont = a.getContinentPopulation("Europe");
        //printContinent
        a.printContinent(cont);

        //get population report
        Population pop = a.getPopulation(null, "world");
        a.printPopulations(pop);

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
    public void connect(String location)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
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
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
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
            // Return new country if valid.
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

    /**
     * Gets the population of a continent
     * @return A Continent or null if there is a error.
     */
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
                cont.population = rset.getInt(1);
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
     * get population based on the SCOPE world (enter null), continent, region, country,
     * district or city.
     *
     */
    public Population getPopulation(String SCOPE, String Sname)
    {
        try
        {
            String strSelect = null;
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            if (SCOPE == null)

            {
                strSelect =
                        "select sum(population) as population from country;";
            }
            else if (SCOPE == "continent")
            {
                strSelect =
                        "select sum(population)as population from country where continent = '" + Sname + "'";
            }
            else if (SCOPE == "region")
            {
                strSelect =
                        "select sum(population)as population from country where Region = '" + Sname + "'";
            }
            else if (SCOPE == "country")
            {
                strSelect =
                        "select sum(population)as population from country where country = '" + Sname + "'";
            }
            else if (SCOPE == "district")
            {
                strSelect =
                "select sum(population)as population from city where district = '" + Sname + "'";
            }
            else if (SCOPE == "city")
            {
                strSelect =
                "select sum(population)as population from city where city = '" + Sname + "'";
            }
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return population and continent if valid.
            // Check one is returned
            if (rset.next())
            {
                Population pop = new Population();
                pop.name = Sname;
                pop.totalPopulation = rset.getInt("population");
                return pop;
            } else
                return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
            }
        }


    /**
     * Prints a list of countries.
     * @param countries The list of countries to print.
     */
            public void printCountry(ArrayList<Country> countries)
            {
                //Check countries is not null
                if (countries == null)
                {
                    System.out.println("No countries");
                    return;
                }
                // Print header
                System.out.println(String.format("%-10s %-15s %-20s %-8s %-8s %-8s", "code", "name", "continent", "region", "population", "capital"));
                // Loop over all countries in the list
                for (Country cnt : countries)
                {
                    if (cnt == null)
                        continue;
                    String country_string =
                            String.format("%-10s %-15s %-20s %-8s %-8s %-8s",
                                    cnt.code, cnt.name, cnt.continent, cnt.region, cnt.population, cnt.capital);
                    System.out.println(country_string);
                }
            }

            public void printPopulations(Population pop)
            {
                if(pop != null) {
                    System.out.println(String.format("%-10s %-15s %-15 %-15", "name", "population", "percentage living in cities", "percentage not in cites"));
                    String population_string =
                            String.format("%-10s %-15s %-15 %-15",
                                    pop.name, pop.totalPopulation, pop.populationLivingInCity, pop.populationNotInCity);
                    System.out.println(population_string);
                }
            }


    /**
     * Prints population of inputted continent.
     * @param cont The continent to print.
     */
            public void printContinent(Continent cont)
            {
                if(cont != null) {
                    System.out.println(String.format("%-10s %-15s", "population", "continent"));
                    String continent_string =
                            String.format("%-10s %-15s",
                                    cont.population, cont.continentName);
                    System.out.println(continent_string);
                }
            }
    }
