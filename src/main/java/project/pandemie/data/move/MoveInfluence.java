package project.pandemie.data.move;

public class MoveInfluence extends Move {

    private String type = "exertInfluence";
    private String city;

    public MoveInfluence(String city) {
        super(0);
        this.city = city;
    }

    @Override
    public int getCost() {
        return 3;
    }
}
