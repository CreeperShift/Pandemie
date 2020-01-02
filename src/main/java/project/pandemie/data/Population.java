package project.pandemie.data;

import java.util.List;

public class Population {

    private int totalPop;
    private int totalInf;
    private double percentInf;

    public void addPopulation(int population, List<Double> prevalence) {
        this.totalPop += population;
        for (Double f : prevalence) {
            totalInf += getInf(population, f);
        }
        percentInf = (double) totalInf / (double) totalPop;
    }

    public void addPopulation(int population) {
        this.totalPop += population;
    }


    private int getInf(int a, double b) {
        return (int) Math.round((double) a * b);
    }

    public int getPopulation() {
        return totalPop;
    }

    public int getInfectedPopulation() {
        return totalInf;
    }

    public double getPercentInf() {
        return percentInf;
    }
}
