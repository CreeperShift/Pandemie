package project.pandemie.data.move;

public class MoveHygienic extends Move{

    private String type = "applyHygienicMeasures";
    private String city;

    public MoveHygienic(String city) {
        super(0);
        this.city = city;
    }

    @Override
    public int getCost() {
        return 3;
    }

}
