package project.pandemie.data;

import project.pandemie.util.UtilHelper;

import java.util.Objects;

public class Pathogen {

    public static class Builder {

        private String name;
        private String infectivity;
        private String mobility;
        private String duration;
        private String lethality;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setInfectivity(String infectivity) {
            this.infectivity = infectivity;
            return this;
        }

        public Builder setMobility(String mobility) {
            this.mobility = mobility;
            return this;
        }

        public Builder setDuration(String duration) {
            this.duration = duration;
            return this;
        }

        public Builder setLethality(String lethality) {
            this.lethality = lethality;
            return this;
        }

        public Pathogen build() {
            Pathogen pathogen = new Pathogen();
            pathogen.name = name;
            pathogen.infectivity = infectivity;
            pathogen.mobility = mobility;
            pathogen.duration = duration;
            pathogen.lethality = lethality;
            return pathogen;
        }
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pathogen pathogen = (Pathogen) o;
        return pathogenScore == pathogen.pathogenScore &&
                name.equals(pathogen.name) &&
                infectivity.equals(pathogen.infectivity) &&
                mobility.equals(pathogen.mobility) &&
                duration.equals(pathogen.duration) &&
                lethality.equals(pathogen.lethality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, infectivity, mobility, duration, lethality);
    }
}
