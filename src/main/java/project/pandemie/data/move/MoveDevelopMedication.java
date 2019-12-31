package project.pandemie.data.move;

public class MoveDevelopMedication extends Move {

    private String type = "developMedication";
    private String pathogen;

    public MoveDevelopMedication(String pathogen) {
        super(0);
        this.pathogen = pathogen;
    }

    @Override
    public int getCost() {
        return 20;
    }
}
