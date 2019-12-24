package project.pandemie.data;

import project.pandemie.util.UtilHelper;

public class Pathogen {

    private String name;
    private String infectivity;
    private String mobility;
    private String duration;
    private String lethality;

    private transient int pathogenScore = 0; //TODO: Determine score!

    public String getName() {
        return name;
    }

    public int getPathogenScore() {
        return pathogenScore;
    }

    public int getInfectivity() {
        return UtilHelper.stringValueToNumeric(infectivity);
    }

    public int getMobility() {
        return UtilHelper.stringValueToNumeric(mobility);
    }

    public int getDuration() {
        return UtilHelper.stringValueToNumeric(duration);
    }

    public int getLethality() {
        return UtilHelper.stringValueToNumeric(lethality);
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
