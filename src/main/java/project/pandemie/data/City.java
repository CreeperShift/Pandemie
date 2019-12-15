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

    private int popInfected;

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
        return convertValues(economy);
    }

    public int getGovernment() {
        return convertValues(government);
    }

    public int getHygiene() {
        return convertValues(hygiene);
    }

    public int getAwareness() {
        return convertValues(awareness);
    }

    public Events[] getEvents() {
        return events;
    }

    public int getPopInfected(){
        return popInfected;
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

    public boolean hasEvents(){
        return getEvents() != null && getEvents().length > 0;
    }

    public void calculateInfected(){
        if(hasEvents()){
            for (Events events : getEvents()){
                if(events.getPathogen() != null){
                   popInfected = (int)(population * events.getPrevalence());
                }
            }
        }
    }

    private int convertValues(String value){
        switch(value){
            case "--" : return -2;
            case "-" : return -1;
            case "0" : return 0;
            case "+" : return 1;
            case "++" : return 2;
        }
        return 0;
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
