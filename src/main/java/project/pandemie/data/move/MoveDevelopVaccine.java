package project.pandemie.data.move;

public class MoveDevelopVaccine extends Move{

    private String type = "developVaccine";
    private String pathogen;

    public MoveDevelopVaccine(String pathogen) {
        super(0);
        this.pathogen = pathogen;
    }

    @Override
    public int getCost() {
        return 40;
    }
}
