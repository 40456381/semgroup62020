package com.napier.semgroup62020;

import com.napier.sem.group6.App;
import com.napier.sem.group6.Continent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060");
    }
    //test the getContinentPopulation method
    @Test
    void testGetContinentPopulation()
    {
        Continent cont = app.getContinentPopulation("North America");
        assertEquals(cont.getContinentName(), "North America");
        assertEquals(cont.getPopulation(), 482993000);
    }
}
