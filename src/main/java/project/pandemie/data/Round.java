package project.pandemie.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class Round {

    public static class Builder {
        private int round;
        private String outcome;
        private int points;
        private Map<String, City> cities;
        private Collection<Events> events;

        public Builder(int round, String outcome) {
            this.round = round;
            this.outcome = outcome;
        }

        public Builder withPoints(int points) {
            this.points = points;
            return this;
        }

        public Builder withCities(Map<String, City> cities) {
            this.cities = cities;
            return this;
        }

        public Builder withEvents(Collection<Events> events) {
            this.events = events;
            return this;
        }

        public Round build() {
            Round r = new Round();
            r.round = round;
            r.outcome = outcome;
            r.points = points;
            r.cities = cities;
            r.events = events;
            r.worldPopulation = calculatePopulation();
            r.infectedPopulation = calculateInfected();

            return r;
        }

        /*
        Calculate world population
        */
        private int calculatePopulation() {
            int pop = 0;
            for (City c : cities.values()) {
                pop += c.getPopulation();
            }
            return pop;
        }

        /*
        Calculate infected world population
        */
        private int calculateInfected() {
            int pop = 0;
            for (City c : cities.values()) {
                pop += c.getInfectedPopulation();
            }
            return pop;
        }
    }

    private Round() {

    }

    private String outcome;
    private int round;
    private int points;
    private Map<String, City> cities;
    private Collection<Events> events;
    private int worldPopulation;
    private int infectedPopulation;


    public String getOutcome() {
        return outcome;
    }

    public int getRound() {
        return round;
    }

    public int getPoints() {
        return points;
    }

    public Map<String, City> getCities() {
        return cities;
    }

    public Collection<Events> getEvents() {
        return events;
    }

    public int getWorldPopulation() {
        return worldPopulation;
    }

    public int getInfectedPopulation() {
        return infectedPopulation;
    }

    public float getPercentInfected() {
        return ((float)getInfectedPopulation() / (float)getWorldPopulation()) * 100f;
    }

    public Collection<City> getInfectedCities() {
        Collection<City> col = new ArrayList<City>();

        for (City c : cities.values()) {
            if (c.isInfected()){
                col.add(c);
            }
        }
        return col;
    }

    @Override
    public String toString() {
        return "Round{" +
                "outcome='" + outcome + '\'' +
                ", round=" + round +
                ", points=" + points +
                ", worldPopulation=" + worldPopulation +
                ", infectedPopulation=" + infectedPopulation +
                ", percentInfected=" + getPercentInfected() +
                '}';
    }
}
