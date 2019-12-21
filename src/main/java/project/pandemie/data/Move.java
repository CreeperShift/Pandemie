package project.pandemie.data;

//TODO: Convert to Factory Pattern!

public class Move {

    public static class Builder {

        /*
        Builder pattern to create our Move
        Type is not optional.
         */

        private String type;
        private String city;
        private Integer rounds;
        private String fromCity;
        private String toCity;
        private String pathogen;

        public Builder(String type){
            this.type = type;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withRounds(int rounds) {
            this.rounds = rounds;
            return this;
        }

        public Builder withFromCity(String fromCity) {
            this.fromCity = fromCity;
            return this;
        }

        public Builder withToCity(String toCity) {
            this.toCity = toCity;
            return this;
        }

        public Builder withPathogen(String pathogen) {
            this.pathogen = pathogen;
            return this;
        }


        public Move build(){
            Move move = new Move();
            move.type = type;
            move.pathogen = pathogen;
            move.toCity = toCity;
            move.rounds = rounds;
            move.city = city;
            move.fromCity = fromCity;

            return move;
        }
    }



    private String type;
    private String city;
    private Integer rounds;
    private String fromCity;
    private String toCity;
    private String pathogen;

    private Move(){

    }

    @Override
    public String toString() {
        return "Move{" +
                "type='" + type + '\'' +
                ", city='" + city + '\'' +
                ", rounds=" + rounds +
                ", fromCity='" + fromCity + '\'' +
                ", toCity='" + toCity + '\'' +
                ", pathogen='" + pathogen + '\'' +
                '}';
    }
}
