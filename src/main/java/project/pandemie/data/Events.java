package project.pandemie.data;


public class Events {

    private String type;
    private Integer round;
    private Pathogen pathogen;
    private Integer sinceRound;
    private double prevalence;
    private Integer untilRound;

    public String getType() {
        return type;
    }

    public int getRound() {
        return round;
    }

    public Pathogen getPathogen() {
        return pathogen;
    }

    public int getSinceRound() {
        return sinceRound;
    }

    public double getPrevalence() {
        return prevalence;
    }

    public int getUntilRound() {
        return untilRound;
    }

    @Override
    public String toString() {
        return "Events{" +
                "type='" + type + '\'' +
                ", round=" + round +
                ", pathogen=" + pathogen +
                ", sinceRound=" + sinceRound +
                ", prevalence=" + prevalence +
                ", untilRound=" + untilRound +
                '}';
    }

}
