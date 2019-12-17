package project.pandemie.data;

import project.pandemie.util.ConversionHelper;

public class Pathogen {

    private String name;
    private String infectivity;
    private String mobility;
    private String duration;
    private String lethality;

    public String getName() {
        return name;
    }

    public int getInfectivity() {
        return ConversionHelper.stringValueToNumeric(infectivity);
    }

    public int getMobility() {
        return ConversionHelper.stringValueToNumeric(mobility);
    }

    public int getDuration() {
        return ConversionHelper.stringValueToNumeric(duration);
    }

    public int getLethality() {
        return ConversionHelper.stringValueToNumeric(lethality);
    }

    @Override
    public String toString() {
        return "Pathogen: " + name + '\'' +
                ", infectivity='" + getInfectivity() + " | " +
                ", mobility='" + getMobility() + " | " +
                ", duration='" + getDuration() + " | " +
                ", lethality='" + getLethality();
    }
}
