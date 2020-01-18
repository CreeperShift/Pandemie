package project.pandemie.data;

import java.util.*;

public class Round {

    public static class Builder {
        private int round;
        private String outcome;
        private int points;
        private CityWrapper cityWrapper;
        private Collection<Events> events;

        public Builder(int round, String outcome) {
            this.round = round;
            this.outcome = outcome;
        }

        public Builder withPoints(int points) {
            this.points = points;
            return this;
        }

        public Builder withCities(CityWrapper cityWrapper) {
            this.cityWrapper = cityWrapper;
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
            r.cityWrapper = cityWrapper;
            r.events = events;
            r.pathogens = getPathogens();

            return r;
        }

        private List<Pathogen> getPathogens() {
            List<Pathogen> pathogenList = new ArrayList<>();

            for (Events e : events) {
                if (e.getPathogen() != null) {
                    pathogenList.add(e.getPathogen());
                }
            }
            return pathogenList;
        }

    }

    private Round() {
    }

    private String outcome;
    private int round;
    private int points;
    private CityWrapper cityWrapper;
    private Collection<Events> events;
    private List<Pathogen> pathogens;


    public String getOutcome() {
        return outcome;
    }

    public int getRound() {
        return round;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Map<String, City> getCities() {
        return cityWrapper.getCities();
    }

    public Collection<Events> getEvents() {
        return events;
    }

    public CityWrapper getCityWrapper() {
        return cityWrapper;
    }

    public List<Pathogen> getPathogens() {
        return pathogens;
    }

    public boolean haveVaccines() {
        return testEventType("vaccineAvailable");
    }

    public boolean isDevelopVaccine() {
        return testEventType("vaccineInDevelopment");
    }

    public boolean haveMedication() {
        return testEventType("medicationAvailable");
    }

    private boolean testEventType(String type) {
        for (Events e : events) {
            if (e.getType().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

    public List<Events> getEventByType(String type) {
        List<Events> returnList = new ArrayList<>();
        for (Events e : events) {
            if (e.getType().equalsIgnoreCase(type)) {
                returnList.add(e);
            }
        }
        return returnList;
    }

    @Override
    public String toString() {
        return "Round{" +
                "outcome='" + outcome + '\'' +
                ", round=" + round +
                ", points=" + points +
                ", worldPopulation=" + cityWrapper.getPopulation().size() +
                ", infectedPopulation=" + cityWrapper.getPopulation().getInfectedPopulation() +
                ", percentInfected=" + cityWrapper.getPopulation().getPercentInfected() +
                '}';
    }
}
