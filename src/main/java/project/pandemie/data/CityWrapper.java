package project.pandemie.data;

import java.util.HashMap;

public class CityWrapper {

    public HashMap<String, City> cityMap;
    private int worldPopulation;
    private int infectedPopulation;
    private int biggestPopulation;

    public CityWrapper(HashMap<String, City> cityMap) {
        this.cityMap = cityMap;
        cityMap.forEach((k, v) -> v.process());
        this.worldPopulation = calculatePopulation();
        this.infectedPopulation = calculateInfected();
        this.biggestPopulation = calculateBiggestPopulation();
    }

    /*
    Calculate world population
    */
    private int calculatePopulation() {
        int pop = 0;
        for (City c : cityMap.values()) {
            pop += c.getPopulation();
        }
        return pop;
    }

    /*
    Calculate infected world population
    */
    private int calculateInfected() {
        int pop = 0;
        for (City c : cityMap.values()) {
            pop += c.getInfectedPopulation();
        }
        return pop;
    }

    public HashMap<String, City> getCities() {
        return cityMap;
    }


    private int calculateBiggestPopulation() {
        int pop = 0;
        for (City c : cityMap.values()) {
            if (c.getPopulation() > pop) {
                pop = c.getPopulation();
            }
        }
        return pop;
    }

    public int getWorldPopulation() {
        return worldPopulation;
    }

    public int getInfectedPopulation() {
        return infectedPopulation;
    }

    public int getBiggestPopulation() {
        return biggestPopulation;
    }
}
