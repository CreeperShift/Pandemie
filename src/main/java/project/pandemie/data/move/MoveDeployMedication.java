package project.pandemie.data.move;

public class MoveDeployMedication extends Move {

    private String type = "deployMedication";
    private String pathogen;
    private String city;

    public MoveDeployMedication(String pathogen, String city) {
        super(0);
        this.pathogen = pathogen;
        this.city = city;
    }

    @Override
    public int getCost() {
        return 10;
    }
}
