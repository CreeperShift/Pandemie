package project.pandemie.data.move;

public class MoveElection extends Move{

    private String type = "callElections";
    private String city;

    public MoveElection(String city) {
        super(0);
        this.city = city;
    }

    @Override
    public int getCost() {
        return 3;
    }
}
