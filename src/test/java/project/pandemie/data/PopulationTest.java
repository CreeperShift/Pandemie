package project.pandemie.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class PopulationTest {

    static Population population;

    @BeforeEach
    void setUp() {
        population = new Population();
        ArrayList<Double> list = new ArrayList<>();
        list.add(0.1d);
        population.addPopulation(10, list);
    }

    @Test
    @DisplayName("Population int")
    void addPopulation() {
        population.addPopulation(5);
        Assertions.assertEquals(15, population.size());
    }

    @Test
    @DisplayName("Population int, double")
    void testAddPopulation() {
        ArrayList<Double> list = new ArrayList<>();
        list.add(0.1d);
        population.addPopulation(10, list);
        Assertions.assertEquals(20, population.size());
        Assertions.assertEquals(2, population.getInfectedPopulation());
    }

    @Test
    @DisplayName("Population object")
    void testAddPopulation1() {
        ArrayList<Double> list = new ArrayList<>();
        list.add(0.1d);
        Population b = new Population();
        b.addPopulation(10, list);
        population.addPopulation(b);
        Assertions.assertEquals(20, population.size());
        Assertions.assertEquals(2, population.getInfectedPopulation());
    }

    @Test
    void size() {
        Assertions.assertEquals(10, population.size());
    }

    @Test
    void getInfectedPopulation() {
        Assertions.assertEquals(1, population.getInfectedPopulation());
    }

    @Test
    void getPercentInfected() {
        Assertions.assertEquals(1, population.getInfectedPopulation());
    }
}