package project.pandemie.data.move;

public class MoveDeployVaccine extends Move {

    private String type = "deployVaccine";
    private String city;

    public MoveDeployVaccine(String city) {
        super(0);
        this.city = city;
    }

    @Override
    public int getCost() {
        return 5;
    }
}
