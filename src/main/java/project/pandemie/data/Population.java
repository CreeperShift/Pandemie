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
            calculatePercent();
        }
    }

    public void addPopulation(int population) {
        this.totalPop += population;
        calculatePercent();
    }

    public void addPopulation(Population p) {
        this.totalPop += p.size();
        this.totalInf += p.totalInf;
        calculatePercent();
    }


    private int getInf(int a, double b) {
        return (int) Math.round((double) a * b);
    }

    private void calculatePercent() {
        percentInf = (double) totalInf / (double) totalPop;
    }

    public int size() {
        return totalPop;
    }

    public int getInfectedPopulation() {
        return totalInf;
    }

    public double getPercentInfected() {
        return percentInf;
    }
}
