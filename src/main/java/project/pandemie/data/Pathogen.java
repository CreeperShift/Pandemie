package project.pandemie.data;

public class Pathogen {

    private String name;
    private String infectivity;
    private String mobility;
    private String duration;
    private String lethality;

    public String getName() {
        return name;
    }

    public String getInfectivity() {
        return infectivity;
    }

    public String getMobility() {
        return mobility;
    }

    public String getDuration() {
        return duration;
    }

    public String getLethality() {
        return lethality;
    }

    @Override
    public String toString() {
        return "Pathogen{" +
                "name='" + name + '\'' +
                ", infectivity='" + infectivity + '\'' +
                ", mobility='" + mobility + '\'' +
                ", duration='" + duration + '\'' +
                ", lethality='" + lethality + '\'' +
                '}';
    }
}
