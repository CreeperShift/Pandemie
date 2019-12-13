package project.pandemie.data;

import java.util.Collection;
import java.util.Map;

public class Round {

    public static class Builder{
        private int round;
        private String outcome;
        private int points;
        private Map<String, City> cities;
        private Collection<Events> events;

        public Builder(int round, String outcome){
            this.round = round;
            this.outcome = outcome;
        }

        public Builder withPoints(int points){
            this.points = points;
            return this;
        }

        public Builder withCities(Map<String, City> cities){
            this.cities = cities;
            return this;
        }

        public Builder withEvents(Collection<Events> events){
            this.events = events;
            return this;
        }

        public Round build(){
            Round r = new Round();
            r.round = round;
            r.outcome = outcome;
            r.points = points;
            r.cities = cities;
            r.events = events;

            /*
            Calculate world population
             */
            int pop = 0;
            for(City c : cities.values()){
                pop += c.getPopulation();
            }
            r.worldPopulation = pop;


            return r;
        }

    }

    private Round(){

    }

    private String outcome;
    private int round;
    private int points;
    private Map<String, City> cities;
    private Collection<Events> events;
    private int worldPopulation;


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

    @Override
    public String toString() {
        return "Round{" +
                "outcome='" + outcome + '\'' +
                ", round=" + round +
                ", points=" + points +
                ", cities=" + cities +
                ", events=" + events +
                '}';
    }
}
