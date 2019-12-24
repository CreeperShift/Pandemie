package project.pandemie.data;

import project.pandemie.util.UtilHelper;

import java.util.Objects;

public class City {

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

    private boolean isInfected = false;
    private int popInfected;
    private int score;

    public void init() {
        checkInfected();
        calculateInfected();
        calculateScore();
    }

    private void calculateScore() {
        score += getEconomy() + getGovernment() + getAwareness() + getHygiene();
    }

    private void checkInfected() {
        if (hasEvents()) {
            for (Events events : this.getEvents()) {
                if (events.getPathogen() != null) {
                    isInfected = true;
                    break;
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getPopulation() {
        return population;
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

    public int getInfectedPopulation() {
        return popInfected;
    }

    public boolean isInfected() {
        return isInfected;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;

    }

//    @Override
//    public String toString() {
//        return "City{" +
//                "name='" + name + '\'' +
//                ", latitude=" + latitude +
//                ", longitude=" + longitude +
//                ", population=" + population +
//                ", connections=" + Arrays.toString(connections) +
//                ", economy='" + getEconomy()+ '\'' +
//                ", government='" + getGovernment() + '\'' +
//                ", hygiene='" + getHygiene() + '\'' +
//                ", awareness='" + getAwareness() + '\'' +
//                ", events=" + Arrays.toString(events) +
//                '}';
//    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", score=" + score + '\'' +
                ", infected?=" + isInfected + '\'' +
                ", population=" + population +
                '}';
    }

    public boolean hasEvents() {
        return getEvents() != null && getEvents().length > 0;
    }

    public void calculateInfected() {
        if (isInfected) {
            for (Events events : getEvents()) {
                if (events.getPathogen() != null) {
                    popInfected = (int) (population * events.getPrevalence());
                }
            }
        }
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
