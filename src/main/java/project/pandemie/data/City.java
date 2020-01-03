package project.pandemie.data;

import project.pandemie.util.UtilHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class City {

    public static class Builder {

        private String name;
        private double latitude;
        private double longitude;
        private int population;
        private String[] connections;
        private String economy;
        private String government;
        private String hygiene;
        private String awareness;
        private Events[] events;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setLatitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder setPopulation(int population) {
            this.population = population;
            return this;
        }

        public Builder setConnections(String[] connections) {
            this.connections = connections;
            return this;
        }

        public Builder setEconomy(String economy) {
            this.economy = economy;
            return this;
        }

        public Builder setGovernment(String government) {
            this.government = government;
            return this;
        }

        public Builder setHygiene(String hygiene) {
            this.hygiene = hygiene;
            return this;
        }

        public Builder setAwareness(String awareness) {
            this.awareness = awareness;
            return this;
        }

        public Builder setEvents(Events[] events) {
            this.events = events;
            return this;
        }

        public City build() {
            City c = new City();
            c.name = name;
            c.longitude = longitude;
            c.population = population;
            c.connections = connections;
            c.economy = economy;
            c.government = government;
            c.latitude = latitude;
            c.hygiene = hygiene;
            c.awareness = awareness;
            c.events = events;
            return c;
        }
    }

    private String name;
    private double latitude;
    private double longitude;
    private int population;
    private String[] connections;
    private String economy;
    private String government;
    private String hygiene;
    private String awareness;
    private Events[] events;

    private boolean isInfected;
    public ScoreHolder scoreHolder;
    public Population pop;

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Population getPopulation() {
        return pop;
    }

    public String[] getConnections() {
        return connections;
    }

    public int getEconomy() {
        return UtilHelper.stringValueToNumeric(economy);
    }

    public int getGovernment() {
        return UtilHelper.stringValueToNumeric(government);
    }

    public int getHygiene() {
        return UtilHelper.stringValueToNumeric(hygiene);
    }

    public int getAwareness() {
        return UtilHelper.stringValueToNumeric(awareness);
    }

    public Events[] getEvents() {
        return events;
    }

    public ScoreHolder getScoreHolder() {
        return scoreHolder;
    }

    public boolean isCityInfected() {
        return isInfected;
    }

    public void process() {
        calculateScores();
        isInfected = isInfected();
        pop = calculatePopulation();
    }

    private void calculateScores() {
        scoreHolder = new ScoreHolder(getEconomy() + getGovernment() + getAwareness() + getHygiene());
    }

    private boolean isInfected() {
        if (!hasEvents()) {
            return false;
        }
        for (Events e : events) {
            if (e.getPathogen() != null) {
                return true;
            }
        }
        return false;
    }

    private Population calculatePopulation() {
        if (isInfected) {
            List<Double> prevalence = new ArrayList<>();
            for (Events e : events) {
                if (e.getPathogen() != null) {
                    prevalence.add(e.getPrevalence());
                }
            }
            Population p = new Population();
            p.addPopulation(population, prevalence);
            return p;
        }
        Population p = new Population();
        p.addPopulation(population);
        return p;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", score=" + scoreHolder + '\'' +
                ", infected?=" + isInfected + '\'' +
                ", population=" + population +
                '}';
    }

    public boolean hasEvents() {
        return getEvents() != null && getEvents().length > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(getName(), city.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
