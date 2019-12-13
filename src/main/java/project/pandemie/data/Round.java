package project.pandemie.data;

import java.util.Collection;
import java.util.Map;

public class Round {

    private String outcome;
    private int round;
    private int points;
    private Map<String, City> cities;
    private Collection<Events> events;

    public void setOutcome(String outcome){
        this.outcome = outcome;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setCities(Map<String, City> cities) {
        this.cities = cities;
    }

    public void setEvents(Collection<Events> events) {
        this.events = events;
    }

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
