package project.pandemie.data;

import java.util.Arrays;
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

    public String getEconomy() {
        return economy;
    }

    public String getGovernment() {
        return government;
    }

    public String getHygiene() {
        return hygiene;
    }

    public String getAwareness() {
        return awareness;
    }

    public Events[] getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", population=" + population +
                ", connections=" + Arrays.toString(connections) +
                ", economy='" + economy + '\'' +
                ", government='" + government + '\'' +
                ", hygiene='" + hygiene + '\'' +
                ", awareness='" + awareness + '\'' +
                ", events=" + Arrays.toString(events) +
                '}';
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
