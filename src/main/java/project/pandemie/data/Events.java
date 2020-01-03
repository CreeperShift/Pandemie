package project.pandemie.data;


public class Events {

    public static class Builder {

        private String type;
        private Integer round;
        private Pathogen pathogen;
        private Integer sinceRound;
        private double prevalence;
        private Integer untilRound;
        private int participants;

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setRound(int round) {
            this.round = round;
            return this;
        }

        public Builder setPathogen(Pathogen pathogen) {
            this.pathogen = pathogen;
            return this;
        }

        public Builder setSinceRound(int sinceRound) {
            this.sinceRound = sinceRound;
            return this;
        }

        public Builder setPrevalence(double prevalence) {
            this.prevalence = prevalence;
            return this;
        }

        public Builder setUntilRound(int untilRound) {
            this.untilRound = untilRound;
            return this;
        }

        public Builder setParticipants(int participants) {
            this.participants = participants;
            return this;
        }

        public Events build() {
            Events events = new Events();
            events.type = type;
            events.round = round;
            events.pathogen = pathogen;
            events.sinceRound = sinceRound;
            events.prevalence = prevalence;
            events.untilRound = untilRound;
            events.participants = participants;

            return events;
        }
    }

    private String type;
    private Integer round;
    private Pathogen pathogen;
    private Integer sinceRound;
    private double prevalence;
    private Integer untilRound;
    private Integer participants;


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

    public int getParticipants() {
        return participants;
    }

    public boolean hasPathogen() {
        return pathogen != null;
    }

    @Override
    public String toString() {
        return "Event: " + getType();
    }

}
